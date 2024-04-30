package com.sweaters.demo1.api;


import com.sweaters.demo1.domain.Sellers;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class SellerModelAssembler implements RepresentationModelAssembler<Sellers, EntityModel<Sellers>> {
    public SellerModelAssembler() {
    }

    public EntityModel<Sellers> toModel(Sellers sellers) {
        return EntityModel.of(sellers, new Link[]{WebMvcLinkBuilder.linkTo(((SellerController)WebMvcLinkBuilder.methodOn(SellerController.class, new Object[0])).findById(sellers.getId())).withSelfRel(), WebMvcLinkBuilder.linkTo(((SellerController)WebMvcLinkBuilder.methodOn(SellerController.class, new Object[0])).all()).withRel("sellers")});
    }
}
