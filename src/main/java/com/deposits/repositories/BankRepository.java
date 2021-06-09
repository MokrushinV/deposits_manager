package com.deposits.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.deposits.entities.BankEntity;

/**
 * Spring Data JPA Repository for {@link BankEntity}
 * @author Mokrushin Vladimir
 *
 */
@Repository
public interface BankRepository extends JpaRepository <BankEntity, Integer> {
	
	@Query("select b from BankEntity b where b.bankName = :name")
	BankEntity findByName (@Param ("name") String name);
}
