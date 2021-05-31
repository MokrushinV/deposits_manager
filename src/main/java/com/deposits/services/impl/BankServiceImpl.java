package com.deposits.services.impl;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.deposits.services.BankService;
import com.deposits.entities.BankEntity;
import com.deposits.repositories.BankRepository;

@Service
public class BankServiceImpl implements BankService {

	@Autowired
	private BankRepository bankRepository;

	@Override
	public BankEntity addBank (BankEntity bankEntity) {
		BankEntity savedBank = bankRepository.saveAndFlush (bankEntity);
		return savedBank;
	}

	@Override
	public void deleteBank (Integer id) {
		bankRepository.deleteById (id);
	}

	@Override
	public Optional <BankEntity> getById (Integer id) {
		return bankRepository.findById(id);
	}
	
	@Override
	public BankEntity getByName (String name) {
		return bankRepository.findByName (name);
	}

	@Override
	public BankEntity editBank (BankEntity bankEntity) {
		return bankRepository.saveAndFlush (bankEntity);
	}

	@Override
	public List <BankEntity> getAll () {
		return bankRepository.findAll ();
	}

	@Override
	public List <BankEntity> getAllSortedByName() {
		return bankRepository.findAll (Sort.by (Sort.Direction.ASC, "bankName"));
	}
}
