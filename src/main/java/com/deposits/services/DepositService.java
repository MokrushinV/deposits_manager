package com.deposits.services;

import java.util.List;
import java.util.Optional;

import com.deposits.entities.DepositEntity;

public interface DepositService {
	
	DepositEntity addDeposit (DepositEntity depositEntity);
	
	void deleteDeposit (Integer id);
	
	Optional <DepositEntity> getById (Integer id);
	
	DepositEntity getByClientId (Integer id);
	
	DepositEntity getByBankId (Integer id);
	
	DepositEntity editDeposit (DepositEntity depositEntity);
	
	List <DepositEntity> getAll ();
	
	List <DepositEntity> getAllSortedByInterest ();
	
	List <DepositEntity> getAllSortedByMonthsSinceOpen ();
	
	List <DepositEntity> getAllSortedByOpenDate ();
}
