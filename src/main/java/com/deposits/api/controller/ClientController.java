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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.deposits.api.assembler.ClientModelAssembler;
import com.deposits.entities.ClientEntity;
import com.deposits.exception.ClientNotFoundException;
import com.deposits.services.impl.ClientServiceImpl;

@RestController
public class ClientController {

	private final ClientServiceImpl clientServiceImpl;
	private final ClientModelAssembler clientModelAssembler;
	
	ClientController (ClientServiceImpl clientServiceImpl, ClientModelAssembler clientModelAssembler) {
		this.clientServiceImpl = clientServiceImpl;
		this.clientModelAssembler = clientModelAssembler;
	}
	
	@GetMapping ("/clients")
	public CollectionModel <EntityModel <ClientEntity>> allClients () {
		List <EntityModel <ClientEntity>> clients = clientServiceImpl.getAll ().stream ()
				.map (clientModelAssembler::toModel)
				.collect (Collectors.toList ());
		return CollectionModel.of (clients, 
								   linkTo (methodOn (ClientController.class).allClients ()).withSelfRel (),
								   linkTo (methodOn (ClientController.class).allClientsSortedByName ()).withRel ("sort_by_name"),
								   linkTo (methodOn (ClientController.class).allClientsSortedByIncForm ()).withRel ("sort_by_incorporation_form"));
	}
	
	@GetMapping ("/clients/sortedByName")
	public CollectionModel <EntityModel <ClientEntity>> allClientsSortedByName () {
		List <EntityModel <ClientEntity>> clients = clientServiceImpl.getAllSortedByName ().stream ()
				.map (clientModelAssembler::toModel)
				.collect (Collectors.toList ());
		return CollectionModel.of (clients, 
								   linkTo (methodOn (ClientController.class).allClientsSortedByName ()).withSelfRel (),
								   linkTo (methodOn (ClientController.class).allClients ()).withRel ("clients"),
								   linkTo (methodOn (ClientController.class).allClientsSortedByIncForm ()).withRel ("sort_by_incorporation_form"));
	}
	
	@GetMapping ("/clients/sortedByIncForm")
	public CollectionModel <EntityModel <ClientEntity>> allClientsSortedByIncForm () {
		List <EntityModel <ClientEntity>> clients = clientServiceImpl.getAllSortedByIncForm ().stream ()
				.map (clientModelAssembler::toModel)
				.collect (Collectors.toList ());
		return CollectionModel.of (clients, 
								   linkTo (methodOn (ClientController.class).allClientsSortedByIncForm ()).withSelfRel (),
								   linkTo (methodOn (ClientController.class).allClients ()).withRel ("clients"),
								   linkTo (methodOn (ClientController.class).allClientsSortedByName ()).withRel ("sort_by_name"));
	}
	
	@PostMapping ("/clients")
	public ResponseEntity <?> newClient (@RequestBody ClientEntity newClient) {
		EntityModel <ClientEntity> entityModel = clientModelAssembler.toModel (clientServiceImpl.addClient (newClient));
		return ResponseEntity
				.created (entityModel.getRequiredLink (IanaLinkRelations.SELF).toUri ())
				.body (entityModel);
	}
	
	@GetMapping ("/clients/{id}")
	public EntityModel <ClientEntity> getClient (@PathVariable Integer id) {
		ClientEntity clientToGet = clientServiceImpl.getById (id).orElseThrow ( () -> new ClientNotFoundException (id));
		return clientModelAssembler.toModel (clientToGet);
	}
	
	@DeleteMapping ("/clients/{id}") //works for console (curl -X ....)
	public ResponseEntity <?> deleteClientConsole (@PathVariable Integer id) {
		clientServiceImpl.deleteClient (id);
		return ResponseEntity.noContent ().build ();
	}
	
	@GetMapping ("/clients/delete/{id}") //works for browser links
	public ResponseEntity <?> deleteClientWeb (@PathVariable Integer id) {
		clientServiceImpl.deleteClient (id);
		return ResponseEntity.noContent ().build ();
	}
	
	@PutMapping ("/clients/{id}")
	public ResponseEntity <?> editClient (@RequestBody ClientEntity newClient, @PathVariable Integer id) {
		ClientEntity updatedClient = clientServiceImpl.getById (id)
				.map (client -> {
					client.setName (newClient.getName ());
					client.setShortName (newClient.getShortName ());
					client.setAddress (newClient.getAddress ());
					client.setIncorpForm (newClient.getIncorpForm ());
					return clientServiceImpl.addClient (client);
				})
				.orElseGet ( () -> {
					newClient.setId (id);
					return clientServiceImpl.addClient (newClient);
				});
		
		EntityModel <ClientEntity> entityModel = clientModelAssembler.toModel (updatedClient);
		return ResponseEntity
				.created (entityModel.getRequiredLink (IanaLinkRelations.SELF).toUri ())
				.body (entityModel);
	}
	
}
