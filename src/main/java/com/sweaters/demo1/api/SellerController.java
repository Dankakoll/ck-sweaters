package com.sweaters.demo1.api;

import com.sweaters.demo1.domain.Sellers;
import com.sweaters.demo1.exceptions.ItemsNotFoundException;
import com.sweaters.demo1.repository.SellersRepository;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class SellerController {
    private final SellersRepository repository;
    private final SellerModelAssembler assembler;

    SellerController(SellersRepository repository, SellerModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping({"/sellers"})
    CollectionModel<EntityModel<Sellers>> all() {
        List<EntityModel<Sellers>> items = repository.findAll().stream()
                .map(assembler :: toModel).collect(Collectors.toList());
        return CollectionModel.of (items,linkTo(methodOn(ItemController.class).all()).withSelfRel());
    }

    @GetMapping({"/sellers/{Id}"})
    EntityModel<Sellers> findById(@PathVariable Long Id) {
        Sellers sellers = repository.findById(Id).orElseThrow(() -> new ItemsNotFoundException(Id));
        return this.assembler.toModel(sellers);
    }

    @PostMapping(
            value = {"/sellers"},
            consumes = {"application/json"}
    )
    ResponseEntity<?> new_seller(@RequestBody Sellers sellers) {
        EntityModel<Sellers> entityModel = this.assembler.toModel(repository.save(sellers));
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }
}
