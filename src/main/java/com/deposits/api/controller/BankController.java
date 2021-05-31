package com.deposits.api.controller;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
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
		List <EntityModel <BankEntity>> banks = bankServiceImpl.getAll ().stream ()
				.map (bankModelAssembler::toModel)
				.collect (Collectors.toList());
		return CollectionModel.of (banks,
								   linkTo (methodOn (BankController.class).allBanks ()).withSelfRel (),
								   linkTo (methodOn (BankController.class).allBanksSortedByName()).withRel("sort_by_name"));
	}
	
	@GetMapping ("/banks/sortedByName")
	public CollectionModel <EntityModel <BankEntity>> allBanksSortedByName () {
		List <EntityModel <BankEntity>> banks = bankServiceImpl.getAllSortedByName ().stream ()
				.map (bankModelAssembler::toModel)
				.collect (Collectors.toList());
		return CollectionModel.of (banks,
								   linkTo (methodOn (BankController.class).allBanks ()).withRel ("banks"),
								   linkTo (methodOn (BankController.class).allBanksSortedByName ()).withSelfRel ());
	}
	
	@PostMapping ("/banks")
	public ResponseEntity <?> newBank (@RequestBody BankEntity newBank) {
		EntityModel <BankEntity> entityModel = bankModelAssembler.toModel (bankServiceImpl.addBank (newBank));
		return ResponseEntity
				.created (entityModel.getRequiredLink (IanaLinkRelations.SELF).toUri ())
				.body (entityModel);
	}
	
	@GetMapping ("/banks/{id}")
	public EntityModel <BankEntity> getBank (@PathVariable Integer id) {
		BankEntity bankToGet = bankServiceImpl.getById (id).orElseThrow ( () -> new BankNotFoundException (id));
		return bankModelAssembler.toModel (bankToGet);
	}
	//@GetMapping ("/banks/{name}")
	/*
	 * maybe this would be useful in future
	 * 
	 * */
	public BankEntity getBankByName (@PathVariable String name) {
		return bankServiceImpl.getByName (name);
	}
	
	@DeleteMapping ("/banks/{id}") //works for console (curl -X ....)
	public ResponseEntity <?> deleteBankConsole (@PathVariable Integer id) {
		bankServiceImpl.deleteBank (id);
		return ResponseEntity.noContent ().build ();
	}
	
	@GetMapping ("/banks/delete/{id}") //works for browser links
	public ResponseEntity <?> deleteBankWeb (@PathVariable Integer id) {
		bankServiceImpl.deleteBank (id);
		return ResponseEntity.noContent ().build ();
	}
	
	@PutMapping ("/banks/{id}")
	public ResponseEntity <?> editBank (@RequestBody BankEntity newBank, @PathVariable Integer id) {
		BankEntity updatedBank = bankServiceImpl.getById (id)
				.map (bank -> {
					bank.setBankName (newBank.getBankName ());
					bank.setBankBIC (newBank.getBankBIC ());
					return bankServiceImpl.addBank (bank);
				})
				.orElseGet ( () -> {
					newBank.setId (id);
					return bankServiceImpl.addBank (newBank);
				});
		
		EntityModel <BankEntity> entityModel = bankModelAssembler.toModel (updatedBank);
		return ResponseEntity
				.created (entityModel.getRequiredLink (IanaLinkRelations.SELF).toUri ())
				.body (entityModel);
	}
	
}
