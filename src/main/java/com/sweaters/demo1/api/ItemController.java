//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.sweaters.demo1.api;

import com.sweaters.demo1.domain.Items;
import com.sweaters.demo1.domain.Prices;
import com.sweaters.demo1.exceptions.ItemsNotFoundException;
import com.sweaters.demo1.repository.ItemsRepository;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.sweaters.demo1.repository.PricesRepository;
import org.hibernate.type.EntityType;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class ItemController {
    private final ItemsRepository repository;
    private final ItemModelAssembler assembler;

    //Таблицы Prices и Items связаны, поэтому обрабатываем в одном контроллере.
    private final PricesRepository pricesRepository;
    ItemController(ItemsRepository itemsRepository, ItemModelAssembler itemsAssembler,PricesRepository pricesRepository) {
        this.repository = itemsRepository;
        this.assembler = itemsAssembler;
        this.pricesRepository= pricesRepository;
    }

    //Вывод всех элементов
    @GetMapping({"/items"})
    CollectionModel<EntityModel<Items>> all() {
        List<EntityModel<Items>> items = repository.findAll().stream()
                .map(assembler :: toModel).collect(Collectors.toList());
        return CollectionModel.of (items,linkTo(methodOn (ItemController.class).all()).withSelfRel());
    }

    //Вывод по Id
    @GetMapping({"/items/{Id}"})
    EntityModel<Items> findById(@PathVariable Long Id) {
        Items item = repository.findById(Id)
                .orElseThrow(()->new ItemsNotFoundException(Id));
        return assembler.toModel(item);
    }
    //Вывод всех цен по Id предмета (по артикулу и месту сбора)
    @GetMapping ("/items/{Id}/prices")
    List<Prices> allPricesById(@PathVariable Long Id)
    {
        Items item = repository.findById(Id)
                .orElseThrow(()->new ItemsNotFoundException(Id));
        List<Prices> prices= pricesRepository.findBySecondKey(item.getOrigin(),item.getOrigin_id());
        return prices;

    }
    //Добавление предмета с учетом существования
    //Если добавить как один ключ (id,origin,origin_id),
    //от проверки можно будет отказаться
    @PostMapping(
            value = {"/items"},
            consumes = {"application/json"}
    )
    ResponseEntity<?> new_item(@RequestBody Items item) {
        Items existing_item = repository.findBySecondKey(item.getOrigin(), item.getOrigin_id());
        Items items;
        if(existing_item == null)
        {
            items = new Items (item.getItems_name(), item.getPrice(), item.getOrigin(), item.getOrigin_id());
        }
        else
        {
            existing_item.setUpdated_at(item.getUpdated_at());
            existing_item.setPrice(item.getPrice());
            items = existing_item;
        }
        Prices prices = new Prices(items.getPrice(),items.getOrigin(),items.getOrigin_id(),items.getUpdated_at());
        pricesRepository.save(prices);
        EntityModel<Items> entityModel = this.assembler.toModel(repository.save(items));
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    //Замена предмета, пока не адаптированная для Prices
    @PutMapping({"/items"})
    ResponseEntity<?> replaceItem(@RequestBody Items items1, @PathVariable Long Id) {
        Items updated_item = repository.findById(Id).map((items) -> {
            items.setItems_name(items1.getItems_name());
            items.setPrice(items1.getPrice());
            items.setOrigin(items1.getOrigin());
            items.setOrigin_id(items1.getOrigin_id());
            items.setUpdated_at(items1.getUpdated_at());
            return repository.save(items);
        }).orElseGet(() -> {
            items1.setId(Id);
            return repository.save(items1);
        });
        EntityModel<Items> entityModel = this.assembler.toModel(updated_item);
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    //Удаление, также пока не адаптировано для Prices
    @DeleteMapping({"/items/{Id}"})
    ResponseEntity<?> deleteItems(@PathVariable Long Id) {
        this.repository.deleteById(Id);
        return ResponseEntity.noContent().build();
    }
}
