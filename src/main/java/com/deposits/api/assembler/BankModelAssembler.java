package com.deposits.api.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.deposits.api.controller.BankController;
import com.deposits.entities.BankEntity;

/**
 * Modifies {@link BankEntity} with links before responding
 * to user's request. Implicitly using DTO.
 * @author Mokrushin Vladimir
 *
 */
@Component
public class BankModelAssembler implements RepresentationModelAssembler <BankEntity, EntityModel <BankEntity>> {

	@Override
	public EntityModel <BankEntity> toModel (BankEntity bankEntity) {
		return EntityModel.of (bankEntity,
							   linkTo (methodOn (BankController.class).getBank (bankEntity.getId ())).withSelfRel (),
							   linkTo (methodOn (BankController.class).allBanks ()).withRel ("banks"),
							   linkTo (methodOn (BankController.class).deleteBankWeb (bankEntity.getId ())).withRel ("delete"),
							   linkTo (methodOn (BankController.class).getOwnedDeposits (bankEntity.getId ())).withRel ("owned_deposit"));
	}

}
