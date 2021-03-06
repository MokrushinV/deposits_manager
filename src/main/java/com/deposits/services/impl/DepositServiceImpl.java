package com.deposits.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.deposits.entities.DepositEntity;
import com.deposits.repositories.DepositRepository;
import com.deposits.services.DepositService;

/**
 * Spring-oriented implementation for {@link DepositService}.
 * @author Mokrushin Vladimir
 *
 */
@Service
public class DepositServiceImpl implements DepositService {

	@Autowired
	private DepositRepository depositRepository;
	
	@Override
	public DepositEntity addDeposit (DepositEntity depositEntity) {
		DepositEntity savedEntity = depositRepository.saveAndFlush (depositEntity);
		return savedEntity;
	}

	@Override
	public void deleteDeposit (Integer id) {
		depositRepository.deleteById (id);		
	}

	@Override
	public Optional <DepositEntity> getById (Integer id) {
		return depositRepository.findById (id);
	}
	
	@Override
	public DepositEntity getByClientId (Integer id) {
		return depositRepository.findByClientId (id);
	}

	@Override
	public DepositEntity getByBankId (Integer id) {
		return depositRepository.findByBankId (id);
	}

	@Override
	public DepositEntity editDeposit (DepositEntity depositEntity) {
		return depositRepository.saveAndFlush (depositEntity);
	}

	@Override
	public List<DepositEntity> getAll () {
		return depositRepository.findAll ();
	}

	@Override
	public List<DepositEntity> getAllSortedByInterest() {
		return depositRepository.findAll (Sort.by (Sort.Direction.ASC, "interestRate"));
	}

	@Override
	public List<DepositEntity> getAllSortedByMonthsSinceOpen() {
		return depositRepository.findAll (Sort.by (Sort.Direction.ASC, "monthsSinceOpen"));
	}

	@Override
	public List<DepositEntity> getAllSortedByOpenDate() {
		return depositRepository.findAll (Sort.by (Sort.Direction.ASC, "openDate"));
	}

}
