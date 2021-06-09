package com.deposits.services;

import java.util.List;
import java.util.Optional;

import com.deposits.entities.DepositEntity;

/**
 * Service for {@link DepositEntity}
 * @author Mokrushin Vladimir
 *
 */
public interface DepositService {
	
	/**
	 * Adds deposit to database.
	 * 
	 * @param DepositEntity object
	 * @return DepositEntity object that has been added
	 */
	DepositEntity addDeposit (DepositEntity depositEntity);
	
	/**
	 * Deletes deposit by id.
	 * 
	 * @param id of deposit that will be deleted
	 */
	void deleteDeposit (Integer id);
	
	/**
	 * Gets deposit by id.
	 * 
	 * @param id of a deposit to be found
	 * @return DepositEntity object (if any)
	 */
	Optional <DepositEntity> getById (Integer id);
	
	/**
	 * Gets deposit by client's id.
	 * 
	 * @param id of a client whom belongs a deposit
	 * @return DepositEntity object
	 */
	DepositEntity getByClientId (Integer id);
	
	/**
	 * Gets deposit by bank's id.
	 * 
	 * @param id of a bank which owns a deposit
	 * @return DepositEntity object
	 */
	DepositEntity getByBankId (Integer id);
	
	/**
	 *Edits existing deposit.
	 * 
	 * @param DepositEntity object
	 * @return DepositEntity object that has been edited.
	 */
	DepositEntity editDeposit (DepositEntity depositEntity);
	
	/**
	 * Gets all deposits from database.
	 * 
	 * @return List of DepositEntity objects.
	 */
	List <DepositEntity> getAll ();
	
	/**
	 * Gets all deposits from database sorted by interest rate.
	 * 
	 * @return List of DepositEntity objects sorted by interest rate
	 */
	List <DepositEntity> getAllSortedByInterest ();
	
	/**
	 * Gets all deposits from database sorted by number of months
	 * passed since deposit was open.
	 * 
	 * @return List of DepositEntity objects sorted by months since deposit was open
	 */
	List <DepositEntity> getAllSortedByMonthsSinceOpen ();
	
	/**
	 * Gets all deposits from database sorted by deposit's open date.
	 * 
	 * @return List of DepositEntity object sorted by their open dates
	 */
	List <DepositEntity> getAllSortedByOpenDate ();
}
