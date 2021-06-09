package com.deposits.services;

import java.util.List;
import java.util.Optional;

import com.deposits.entities.BankEntity;

/**
 * Service for {@link BankEntity}
 * @author Mokrushin Vladimir
 *
 */
public interface BankService {
	
	/**
	 *Adds bank to database. 
	 * 
	 * @param BankEntity object
	 * @return BankEntity object that has been added
	 */
	BankEntity addBank (BankEntity bankEntity);
	
	/**
	 * Deletes bank by id.
	 * 
	 * @param id of a bank that will be deleted
	 */
	void deleteBank (Integer id);
	
	
	/**
	 * Gets bank by id
	 * 
	 * @param id of a bank to be found
	 * @return BankEntity object (if any)
	 */
	Optional <BankEntity> getById (Integer id);
	
	/**
	 * Gets bank by name
	 * 
	 * @param name of a bank to be found
	 * @return BankEntity object
	 */
	BankEntity getByName (String name);
	
	/**
	 * Edits existing bank.
	 * 
	 * @param BankEntity object
	 * @return BankEntity object that has been edited
	 */
	BankEntity editBank (BankEntity bankEntity);
	
	/**
	 * Gets all banks from database.
	 * 
	 * @return List of BankEntity objects
	 */
	List <BankEntity> getAll ();
	
	/**
	 * Gets all banks from database sorted by name.
	 * 
	 * @return List of BankEntity objects sorted by name (A-Z)
	 */
	List <BankEntity> getAllSortedByName ();
	
}
