package com.deposits.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deposits.entities.ClientEntity;
import com.deposits.repositories.ClientRepository;
import com.deposits.services.ClientService;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientRepository clientRepository;
	
	@Override
	public ClientEntity addClient (ClientEntity clientEntity) {
		ClientEntity savedClient = clientRepository.saveAndFlush (clientEntity);
		return savedClient;
	}

	@Override
	public void deleteClient (Integer id) {
		clientRepository.deleteById (id);
		
	}

	@Override
	public ClientEntity getByName (String name) {
		return clientRepository.findByName (name);
	}

	@Override
	public ClientEntity editClient (ClientEntity clientEntity) {
		return clientRepository.saveAndFlush (clientEntity);
	}

	@Override
	public List<ClientEntity> getAll () {
		return clientRepository.findAll ();
	}

}
