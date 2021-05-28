package com.deposits.services;

import java.util.List;
import java.util.Optional;

import com.deposits.entities.BankEntity;

public interface BankService {
	
	BankEntity addBank (BankEntity bankEntity);
	
	void deleteBank (Integer id);
	
	Optional <BankEntity> getById (Integer id);
	
	BankEntity getByName (String name);
	
	BankEntity editBank (BankEntity bankEntity);
	
	List <BankEntity> getAll ();
}
