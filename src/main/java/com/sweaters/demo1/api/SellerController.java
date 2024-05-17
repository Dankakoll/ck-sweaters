package com.sweaters.demo1.api;

import com.sweaters.demo1.domain.Items;
import com.sweaters.demo1.domain.Sellers;
import com.sweaters.demo1.exceptions.ItemsNotFoundException;
import com.sweaters.demo1.exceptions.SellersNotFoundException;
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

    //Вывод всех продавцов
    @GetMapping({"/sellers"})
    CollectionModel<EntityModel<Sellers>> all() {
        List<EntityModel<Sellers>> sellers = repository.findAll().stream()
                .map(assembler :: toModel).collect(Collectors.toList());
        return CollectionModel.of (sellers,linkTo(methodOn(SellerController.class).all()).withSelfRel());
    }

    //Поиск по Id
    @GetMapping({"/sellers/{Id}"})
    EntityModel<Sellers> findById(@PathVariable Long Id) {
        Sellers sellers = repository.findById(Id)
                .orElseThrow(() -> new SellersNotFoundException(Id));
        return assembler.toModel(sellers);
    }

    //Добавление предмета с учетом существования продавца
    // Если добавить как один ключ (id,origin,origin_id),
    //от проверки можно будет отказаться
    @PostMapping(
            value = {"/sellers"},
            consumes = {"application/json"}
    )
    ResponseEntity<?> new_seller(@RequestBody Sellers sellers) {
        Sellers exs_seller = repository.findBySecondKey(sellers.getOrigin(),sellers.getOrigin_id());
        EntityModel<Sellers> entityModel;
        if (exs_seller == null)
        {
            entityModel = this.assembler.toModel(repository.save(sellers));
            return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
        }
        return ResponseEntity.noContent().build();

    }
    //Поиск по имени
    @GetMapping("/sellers/find_seller_by_name/{Seller_name}")
    CollectionModel<EntityModel<Sellers>> findBySeller_nameLike(@PathVariable String Seller_name)
    {
        String Seller_name_with_upcase = Character.toUpperCase(Seller_name.charAt(0)) + Seller_name
                .substring(1);
        String ans_string="";
        if (Seller_name.charAt(0)==Character.toUpperCase(Seller_name.charAt(0)) && Seller_name.charAt(0)!=' ')
        {
            ans_string = Seller_name + '%';
        }
        else
        {
            ans_string = '%' + Seller_name +'%';
        }
        List<EntityModel<Sellers>> sellers =repository.findBySeller_name(ans_string).stream().map(
                assembler::toModel).collect(Collectors.toList());

        return CollectionModel.of (sellers,linkTo(methodOn(SellerController.class).all()).withSelfRel());

    }
}
