package com.sweaters.demo1.api;

import com.sweaters.demo1.domain.Items;
import com.sweaters.demo1.domain.Sellers;
import com.sweaters.demo1.exceptions.ItemsNotFoundException;
import com.sweaters.demo1.repository.ItemsRepository;
import com.sweaters.demo1.repository.SellersRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class ItemController {

    private final ItemsRepository repository;

    private final ItemModelAssembler assembler;
    ItemController(ItemsRepository repository, SellersRepository repositorySell, ItemModelAssembler assembler)
    {
        this.repository=repository;
        this.assembler=assembler;
    }
    @GetMapping("/items")
    CollectionModel<EntityModel<Items>> all ()
    {
        List<EntityModel<Items>> items = repository.findAll().stream()
                .map(assembler :: toModel).collect(Collectors.toList());
        return CollectionModel.of (items,linkTo(methodOn(ItemController.class).all()).withSelfRel());
    }
    @GetMapping("/items/{Id}")
    EntityModel<Items> findById(@PathVariable Long Id)
    {
        Items item = repository.findById(Id)
                .orElseThrow(()->new ItemsNotFoundException(Id));
        return assembler.toModel(item);
    }
    @PostMapping(value="/items",consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> new_item(@RequestBody Items item)
    {
        Items new_items= new Items(item.getOrigin_name(),item.getPrice());
        EntityModel<Items> entityModel=assembler.toModel(repository.save(new_items));
        return ResponseEntity.
                created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }
    @PutMapping("/items")
    ResponseEntity<?> replaceItem (@RequestBody Items items1,@PathVariable Long Id)
    {
        Items updated_item = repository.findById(Id)
                .map(items -> {
                    items.setOrigin_name(items1.getOrigin_name());
                    items.setPrice(items1.getPrice());
                    items.setUpdated_at(items1.getUpdated_at());
                    return repository.save(items);
                })
                .orElseGet(() -> {
                    items1.setId(Id);
                    return repository.save(items1);
                });
        EntityModel<Items> entityModel= assembler.toModel(updated_item);
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }
    @DeleteMapping("/items/{Id}")
    ResponseEntity<?> deleteItems(@PathVariable Long Id)
    {
        repository.deleteById(Id);
        return ResponseEntity.noContent().build();
    }


}
