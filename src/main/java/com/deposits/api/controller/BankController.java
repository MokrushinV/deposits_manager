package com.deposits.api.controller;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.deposits.entities.BankEntity;
import com.deposits.services.impl.BankServiceImpl;


@RestController
public class BankController {

	private final BankServiceImpl bankServiceImpl;
	
	BankController (BankServiceImpl bankServiceImpl) {
		this.bankServiceImpl = bankServiceImpl;
	}
	
	@GetMapping("/banks")
	List <BankEntity> allBanks () {
		return bankServiceImpl.getAll();
	}
	
	@PostMapping("/banks")
	BankEntity newBank (@RequestBody BankEntity newBank) {
		return bankServiceImpl.addBank(newBank);
	}
	
	@GetMapping("/banks/{name}")
	BankEntity getBank (@PathVariable String name) {
		return bankServiceImpl.getByName(name);
	}
	
	@DeleteMapping("/banks/{id}")
	void deleteBank (@PathVariable Integer id) {
		bankServiceImpl.deleteBank(id);
	}
	
	/*
	 * to-do:
	 * @PutMapping
	 * editBank
	 * */
	
}
