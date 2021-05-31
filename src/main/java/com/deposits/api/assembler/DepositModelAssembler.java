package com.deposits.api.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.deposits.api.controller.DepositController;
import com.deposits.entities.DepositEntity;

@Component
public class DepositModelAssembler implements RepresentationModelAssembler <DepositEntity, EntityModel <DepositEntity>> {

	@Override
	public EntityModel <DepositEntity> toModel (DepositEntity depositEntity) {
 
		return EntityModel.of (depositEntity,
							   linkTo (methodOn (DepositController.class).getDepositById (depositEntity.getId ())).withSelfRel (),
							   linkTo (methodOn (DepositController.class).allDeposits ()).withRel ("deposits"),
							   linkTo (methodOn (DepositController.class).deleteDepositWeb (depositEntity.getId ())).withRel ("delete"));
	}

}
