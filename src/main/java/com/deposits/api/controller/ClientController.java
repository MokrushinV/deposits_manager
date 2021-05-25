package com.deposits.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.deposits.entities.ClientEntity;
import com.deposits.services.impl.ClientServiceImpl;

@RestController
public class ClientController {

	private final ClientServiceImpl clientServiceImpl;
	
	ClientController (ClientServiceImpl clientServiceImpl) {
		this.clientServiceImpl = clientServiceImpl;
	}
	
	@GetMapping("/clients")
	List <ClientEntity> allClients () {
		return clientServiceImpl.getAll();
	}
	
	@PostMapping("/clients")
	ClientEntity newClient (@RequestBody ClientEntity newClient) {
		return clientServiceImpl.addClient(newClient);
	}
	
	@GetMapping("/clients/{name}")
	ClientEntity getClient (@PathVariable String name) {
		return clientServiceImpl.getByName(name);
	}
	
	@DeleteMapping("/clients/{id}")
	void deleteClient (@PathVariable Integer id) {
		clientServiceImpl.deleteClient(id);
	}
	/*@PutMapping("/clients")
	ClientEntity editClient (@RequestBody ClientEntity editedClient) {
		editedClient.getId();
	}
	*внести изменения в ClientSreviceImpl
	*добавить кастомный класс Exception -> (Optional <?> getById)
	*/
	
}
