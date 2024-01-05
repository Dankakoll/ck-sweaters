package com.sweaters.demo1.api;

import com.sweaters.demo1.domain.Items;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public
class ItemModelAssembler implements RepresentationModelAssembler<Items, EntityModel<Items>>
{
    @Override
    public EntityModel<Items> toModel (Items items){
        return EntityModel.of(items,
                linkTo(methodOn(ItemController.class).findById(items.getId())).withSelfRel(),
                linkTo(methodOn(ItemController.class).all()).withRel("items"));
    }
}
