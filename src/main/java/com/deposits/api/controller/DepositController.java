package com.deposits.api.controller;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


import com.deposits.api.assembler.DepositModelAssembler;
import com.deposits.entities.DepositEntity;
import com.deposits.exception.BankNotFoundException;
import com.deposits.exception.ClientNotFoundException;
import com.deposits.exception.DepositNotFoundException;
import com.deposits.services.impl.BankServiceImpl;
import com.deposits.services.impl.ClientServiceImpl;
import com.deposits.services.impl.DepositServiceImpl;

/**
 * API-endpoint for deposit management.
 * @author Mokrushin Vladimir
 *
 */
@RestController 
public class DepositController {
	
private final DepositServiceImpl depositServiceImpl;
private final ClientServiceImpl clientServiceImpl;
private final BankServiceImpl bankServiceImpl;
private final DepositModelAssembler depositModelAssembler;
	
	DepositController (DepositServiceImpl depositServiceImpl,
					   ClientServiceImpl clientServiceImpl, 
					   BankServiceImpl bankServiceImpl,
					   DepositModelAssembler depositModelAssembler) {
		
		this.depositServiceImpl = depositServiceImpl;
		this.clientServiceImpl = clientServiceImpl;
		this.bankServiceImpl = bankServiceImpl;
		this.depositModelAssembler = depositModelAssembler; 
	}
	
	@GetMapping ("/deposits")
	public CollectionModel <EntityModel <DepositEntity>> allDeposits () {
		List <EntityModel <DepositEntity>> deposits = depositServiceImpl.getAll().stream()
			.map (depositModelAssembler::toModel)
			.collect (Collectors.toList ());	
		return CollectionModel.of (deposits,
								   linkTo (methodOn (DepositController.class).allDeposits ()).withSelfRel (),
								   linkTo (methodOn (DepositController.class).allDepositsSortedByInterestRate ()).withRel ("sort_by_interest_rate"),
								   linkTo (methodOn (DepositController.class).allDepositsSortedByMonthsSinceOpen ()).withRel ("sort_by_months_since_open"),
								   linkTo (methodOn (DepositController.class).allDepositsSortedByOpenDate ()).withRel ("sort_by_open_date"));
	}
	
	@GetMapping ("/deposits/sortedByInterestRate")
	public CollectionModel <EntityModel <DepositEntity>> allDepositsSortedByInterestRate () {
		List <EntityModel <DepositEntity>> deposits = depositServiceImpl.getAllSortedByInterest().stream()
			.map (depositModelAssembler::toModel)
			.collect (Collectors.toList ());	
		return CollectionModel.of (deposits,
								   linkTo (methodOn (DepositController.class).allDepositsSortedByInterestRate()).withSelfRel(),
								   linkTo (methodOn (DepositController.class).allDepositsSortedByMonthsSinceOpen ()).withRel ("sort_by_months_since_open"),
								   linkTo (methodOn (DepositController.class).allDepositsSortedByOpenDate ()).withRel ("sort_by_open_date"),
								   linkTo (methodOn (DepositController.class).allDeposits ()).withRel ("deposits"));
	}
	
	@GetMapping ("/deposits/sortedByMonthsSinceOpen")
	public CollectionModel <EntityModel <DepositEntity>> allDepositsSortedByMonthsSinceOpen () {
		List <EntityModel <DepositEntity>> deposits = depositServiceImpl.getAllSortedByMonthsSinceOpen().stream()
			.map (depositModelAssembler::toModel)
			.collect (Collectors.toList ());	
		return CollectionModel.of (deposits,
								   linkTo (methodOn (DepositController.class).allDepositsSortedByMonthsSinceOpen()).withSelfRel(),
								   linkTo (methodOn (DepositController.class).allDepositsSortedByInterestRate ()).withRel ("sort_by_interest_rate"),
								   linkTo (methodOn (DepositController.class).allDepositsSortedByOpenDate ()).withRel ("sort_by_open_date"),
								   linkTo (methodOn (DepositController.class).allDeposits ()).withRel ("deposits"));
	}
	
	@GetMapping ("/deposits/sortedByOpenDate")
	public CollectionModel <EntityModel <DepositEntity>> allDepositsSortedByOpenDate () {
		List <EntityModel <DepositEntity>> deposits = depositServiceImpl.getAllSortedByOpenDate() .stream()
			.map (depositModelAssembler::toModel)
			.collect (Collectors.toList ());	
		return CollectionModel.of (deposits,
								   linkTo (methodOn (DepositController.class).allDepositsSortedByOpenDate()).withSelfRel(),
								   linkTo (methodOn (DepositController.class).allDepositsSortedByInterestRate ()).withRel ("sort_by_interest_rate"),
								   linkTo (methodOn (DepositController.class).allDepositsSortedByMonthsSinceOpen ()).withRel ("sort_by_months_since_open"),
								   linkTo (methodOn (DepositController.class).allDeposits ()).withRel ("deposits"));
	}
	
	@PostMapping ("/deposits")
	public ResponseEntity <?> newDeposit (@RequestBody DepositEntity newDeposit) {
		EntityModel <DepositEntity> entityModel = depositModelAssembler.toModel (depositServiceImpl.addDeposit (newDeposit));
		return ResponseEntity
				.created (entityModel.getRequiredLink (IanaLinkRelations.SELF).toUri())
				.body (entityModel);
	}
	
	@GetMapping ("/deposits/{id}")
	public EntityModel <DepositEntity> getDepositById (@PathVariable Integer id) {
		DepositEntity depositToGet = depositServiceImpl.getById (id).orElseThrow ( () -> new DepositNotFoundException (id));
		return depositModelAssembler.toModel (depositToGet);
	}
	
	//@GetMapping ("/deposits/bank/{id}")
	/*
	 * maybe this would be useful in future
	 * 
	 * */
	public DepositEntity getDepositByClient (@PathVariable Integer id) {
		return depositServiceImpl.getByClientId (id);
	}
	
	//@GetMapping ("/deposits/client/{id}")
	/*
	 * maybe this would be useful in future
	 * 
	 * */
	public DepositEntity getDepositByBank (@PathVariable Integer id) {
		return depositServiceImpl.getByBankId (id);
	}
	
	@DeleteMapping ("/deposits/{id}") //used from console
	public ResponseEntity <?> deleteDepositConsole (@PathVariable Integer id) {
		depositServiceImpl.deleteDeposit (id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping ("/deposits/delete/{id}") //used from browser links
	public ResponseEntity <?> deleteDepositWeb (@PathVariable Integer id) {
		depositServiceImpl.deleteDeposit (id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping ("/deposits/{id}/{clId}/{bnId}")
	public ResponseEntity <?> editDeposit (@RequestBody DepositEntity newDeposit, 
							   @PathVariable (name = "id") Integer id, 
							   @PathVariable (name = "clId") Integer clId, 
							   @PathVariable (name = "bnId") Integer bnId) {
		DepositEntity updatedDeposit = depositServiceImpl.getById(id)
				.map (deposit -> {
					deposit.setInterestRate (newDeposit.getInterestRate ());
					deposit.setOpenDate (newDeposit.getOpenDate ());
					deposit.setMonthsSinceOpen (newDeposit.getMonthsSinceOpen ());
					deposit.setClient (clientServiceImpl.getById (clId).orElseThrow( () -> new ClientNotFoundException (clId)));
					deposit.setBank (bankServiceImpl.getById (bnId).orElseThrow( () -> new BankNotFoundException (bnId)));
					return depositServiceImpl.addDeposit (deposit);
				})
				.orElseGet ( () -> {
					newDeposit.setId (id);
					newDeposit.setClient (clientServiceImpl.getById (clId).orElseThrow( () -> new ClientNotFoundException (clId)));
					newDeposit.setBank (bankServiceImpl.getById (bnId).orElseThrow( () -> new BankNotFoundException (bnId)));
					return depositServiceImpl.addDeposit (newDeposit);
				});
		
		EntityModel <DepositEntity> entityModel = depositModelAssembler.toModel (updatedDeposit);
		return ResponseEntity
				.created (entityModel.getRequiredLink (IanaLinkRelations.SELF).toUri ())
				.body (entityModel);
	}

}
