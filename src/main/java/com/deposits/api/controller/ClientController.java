package com.deposits.api.controller;

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
								   linkTo (methodOn (ClientController.class).allClients ()).withSelfRel ());
	}
	
	@PostMapping ("/clients")
	ClientEntity newClient (@RequestBody ClientEntity newClient) {
		return clientServiceImpl.addClient (newClient);
	}
	
	@GetMapping ("/clients/{id}")
	public EntityModel <ClientEntity> getClient (@PathVariable Integer id) {
		ClientEntity clientToGet = clientServiceImpl.getById (id).orElseThrow ( () -> new ClientNotFoundException (id));
		return clientModelAssembler.toModel (clientToGet);
	}
	
	@DeleteMapping ("/clients/{id}")
	void deleteClient (@PathVariable Integer id) {
		clientServiceImpl.deleteClient (id);
	}
	
	@PutMapping ("/clients/{id}")
	ClientEntity editClient (@RequestBody ClientEntity newClient, @PathVariable Integer id) {
		return clientServiceImpl.getById (id)
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
	}
	
}
