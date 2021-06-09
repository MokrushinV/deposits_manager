package com.deposits.services;

import java.util.List;
import java.util.Optional;

import com.deposits.entities.ClientEntity;

/**
 * Service for {@link ClientEntity}
 * @author Mokrushin Vladimir
 *
 */
public interface ClientService {
	
	/**
	 * Adds client to database.
	 * 
	 * @param ClientEntity object
	 * @return ClientEntity object that has been added
	 */
	ClientEntity addClient (ClientEntity clientEntity);
	
	/**
	 * Deletes client by id.
	 * 
	 * @param id of a client that will be deleted
	 */
	void deleteClient (Integer id);
	
	/**
	 * Gets client by id.
	 * 
	 * @param id of a client to be found
	 * @return ClientEntity object (if any)
	 */
	Optional <ClientEntity> getById (Integer id);
	
	/**
	 *Gets client by name.
	 * 
	 * @param name of a client to be found
	 * @return ClientEntity object
	 */
	ClientEntity getByName (String name);
	
	/**
	 * Edits existing client.
	 * 
	 * @param ClientEntity object
	 * @return ClientEntity object that has been edited
	 */
	ClientEntity editClient (ClientEntity clientEntity);
	
	/**
	 * Gets all clients from database.
	 * 
	 * @return List of ClientEntity objects
	 */
	List <ClientEntity> getAll ();
	
	/**
	 * Gets all clients from database sorted by name.
	 * 
	 * @return List of ClientEntity object sorted by name (A-Z)
	 */
	List <ClientEntity> getAllSortedByName ();
	
	/**
	 * Gets all client from database sorted by incorporation form
	 * 
	 * @return List of ClientEntity object sorted by incorporation form (A-Z)
	 */
	List <ClientEntity> getAllSortedByIncForm ();
}
