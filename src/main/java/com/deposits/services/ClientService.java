package com.deposits.services;

import java.util.List;

import com.deposits.entities.ClientEntity;

public interface ClientService {
	
	ClientEntity addClient (ClientEntity clientEntity);
	
	void deleteClient (Integer id);
	
	ClientEntity getByName (String name);
	
	ClientEntity editClient (ClientEntity clientEntity);
	
	List <ClientEntity> getAll ();
}
