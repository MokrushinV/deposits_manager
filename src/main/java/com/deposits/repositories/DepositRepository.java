package com.deposits.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.deposits.entities.DepositEntity;

@Repository
public interface DepositRepository extends JpaRepository <DepositEntity, Integer> {

	@Query("select d from DepositEntity d where d.client.id = :id") 
	DepositEntity findByClientId (@Param ("id") Integer id);
	
	@Query("select d from DepositEntity d where d.bank.id = :id")
	DepositEntity findByBankId (@Param ("id") Integer id);
}
