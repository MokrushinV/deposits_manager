package com.deposits.api.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.deposits.api.controller.ClientController;
import com.deposits.entities.ClientEntity;

@Component
public class ClientModelAssembler implements RepresentationModelAssembler <ClientEntity, EntityModel <ClientEntity>> {

	@Override
	public EntityModel<ClientEntity> toModel(ClientEntity clientEntity) {
		return EntityModel.of(clientEntity,
							  linkTo (methodOn (ClientController.class).getClient (clientEntity.getId())).withSelfRel(),
							  linkTo (methodOn (ClientController.class).allClients ()).withRel("clients"));
	}
	

}
