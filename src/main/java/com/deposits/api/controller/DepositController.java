package com.deposits.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deposits.entities.DepositEntity;
import com.deposits.exception.BankNotFoundException;
import com.deposits.exception.ClientNotFoundException;
import com.deposits.exception.DepositNotFoundException;
import com.deposits.services.impl.BankServiceImpl;
import com.deposits.services.impl.ClientServiceImpl;
import com.deposits.services.impl.DepositServiceImpl;

@RestController 
public class DepositController {
	
private final DepositServiceImpl depositServiceImpl;
private final ClientServiceImpl clientServiceImpl;
private final BankServiceImpl bankServiceImpl;
	
	DepositController (DepositServiceImpl depositServiceImpl, ClientServiceImpl clientServiceImpl, BankServiceImpl bankServiceImpl) {
		this.depositServiceImpl = depositServiceImpl;
		this.clientServiceImpl = clientServiceImpl;
		this.bankServiceImpl = bankServiceImpl;
	}
	
	@GetMapping("/deposits")
	List <DepositEntity> allDeposits () {
		return depositServiceImpl.getAll();
	}
	
	@PostMapping("/deposits")
	DepositEntity newDeposit (@RequestBody DepositEntity newDeposit) {
		return depositServiceImpl.addDeposit(newDeposit);
	}
	
	@GetMapping("/deposits/{id}")
	DepositEntity getDepositById (@PathVariable Integer id) {
		DepositEntity depositToGet = depositServiceImpl.getById(id).orElseThrow(() -> new DepositNotFoundException (id));
		return depositToGet;
	}
	
	@GetMapping("/deposits/bank/{id}")
	DepositEntity getDepositByClient (@PathVariable Integer id) {
		return depositServiceImpl.getByClientId(id);
	}
	
	@GetMapping("/deposits/client/{id}")
	DepositEntity getDepositByBank (@PathVariable Integer id) {
		return depositServiceImpl.getByBankId(id);
	}
	
	@DeleteMapping("/deposits/{id}")
	void deleteDeposit (@PathVariable Integer id) {
		depositServiceImpl.deleteDeposit(id);
	}
	
	@PutMapping("/deposits/{id}/{clId}/{bnId}")
	DepositEntity editDeposit (@RequestBody DepositEntity newDeposit, 
							   @PathVariable (name = "id") Integer id, 
							   @PathVariable (name = "clId") Integer clId, 
							   @PathVariable (name = "bnId") Integer bnId) {
		return depositServiceImpl.getById(id)
				.map(deposit -> {
					deposit.setInterestRate(newDeposit.getInterestRate());
					deposit.setOpenDate(newDeposit.getOpenDate());
					deposit.setMonthsSinceOpen(newDeposit.getMonthsSinceOpen());
					deposit.setClient(clientServiceImpl.getById(clId).orElseThrow( () -> new ClientNotFoundException(clId)));
					deposit.setBank(bankServiceImpl.getById(bnId).orElseThrow( () -> new BankNotFoundException(bnId)));
					return depositServiceImpl.addDeposit(deposit);
				})
				.orElseGet ( () -> {
					newDeposit.setId (id);
					newDeposit.setClient(clientServiceImpl.getById(clId).orElseThrow( () -> new ClientNotFoundException(clId)));
					newDeposit.setBank(bankServiceImpl.getById(bnId).orElseThrow( () -> new BankNotFoundException(bnId)));
					return depositServiceImpl.addDeposit(newDeposit);
				});
	}

}
