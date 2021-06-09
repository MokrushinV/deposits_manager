package com.deposits.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.deposits.entities.ClientEntity;

/**
 * Spring Data JPA repository for {@link ClientEntity}
 * @author Mokrushin Vladimir
 *
 */
@Repository
public interface ClientRepository extends JpaRepository <ClientEntity, Integer> {
	
	@Query("select c from ClientEntity c where c.name = :name")
	ClientEntity findByName (@Param ("name") String name);

}
