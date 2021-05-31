package com.deposits.services;

import java.util.List;
import java.util.Optional;

import com.deposits.entities.ClientEntity;

public interface ClientService {
	
	ClientEntity addClient (ClientEntity clientEntity);
	
	void deleteClient (Integer id);
	
	Optional <ClientEntity> getById (Integer id);
	
	ClientEntity getByName (String name);
	
	ClientEntity editClient (ClientEntity clientEntity);
	
	List <ClientEntity> getAll ();
	
	List <ClientEntity> getAllSortedByName ();
	
	List <ClientEntity> getAllSortedByIncForm ();
}
