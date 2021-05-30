package com.deposits.api.controller;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.deposits.api.assembler.BankModelAssembler;
import com.deposits.entities.BankEntity;
import com.deposits.exception.BankNotFoundException;
import com.deposits.services.impl.BankServiceImpl;


@RestController
public class BankController {

	private final BankServiceImpl bankServiceImpl;
	private final BankModelAssembler bankModelAssembler;
	
	BankController (BankServiceImpl bankServiceImpl, BankModelAssembler bankModelAssembler) {
		this.bankServiceImpl = bankServiceImpl;
		this.bankModelAssembler = bankModelAssembler;
	}
	
	@GetMapping ("/banks")
	public CollectionModel <EntityModel <BankEntity>> allBanks () {
		List <EntityModel <BankEntity>> banks = bankServiceImpl.getAll ().stream()
				.map (bankModelAssembler::toModel)
				.collect (Collectors.toList());
		return CollectionModel.of (banks,
								   linkTo (methodOn (BankController.class).allBanks ()).withSelfRel ());
	}
	
	@PostMapping ("/banks")
	BankEntity newBank (@RequestBody BankEntity newBank) {
		return bankServiceImpl.addBank (newBank);
	}
	
	@GetMapping ("/banks/{id}")
	public EntityModel <BankEntity> getBank (@PathVariable Integer id) {
		BankEntity bankToGet = bankServiceImpl.getById (id).orElseThrow ( () -> new BankNotFoundException (id));
		return bankModelAssembler.toModel (bankToGet);
	}
	//@GetMapping ("/banks/{name}")
	BankEntity getBankByName (@PathVariable String name) {
		return bankServiceImpl.getByName (name);
	}
	
	@DeleteMapping ("/banks/{id}")
	void deleteBank (@PathVariable Integer id) {
		bankServiceImpl.deleteBank (id);
	}
	
	@PutMapping ("/banks/{id}")
	BankEntity editBank (@RequestBody BankEntity newBank, @PathVariable Integer id) {
		return bankServiceImpl.getById (id)
				.map (bank -> {
					bank.setBankName (newBank.getBankName ());
					bank.setBankBIC (newBank.getBankBIC ());
					return bankServiceImpl.addBank (bank);
				})
				.orElseGet ( () -> {
					newBank.setId (id);
					return bankServiceImpl.addBank (newBank);
				});
	}
	
}
