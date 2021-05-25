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
import com.deposits.services.impl.DepositServiceImpl;

@RestController 
public class DepositController {
	
private final DepositServiceImpl depositServiceImpl;
	
	DepositController (DepositServiceImpl depositServiceImpl) {
		this.depositServiceImpl = depositServiceImpl;
	}
	
	@GetMapping("/deposits")
	List <DepositEntity> allDeposits () {
		return depositServiceImpl.getAll();
	}
	
	@PostMapping("/deposits")
	DepositEntity newDeposit (@RequestBody DepositEntity newDeposit) {
		return depositServiceImpl.addDeposit(newDeposit);
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
	
	/*
	 * to-do:
	 * @PutMapping
	 * editDeposit
	 * */

}
