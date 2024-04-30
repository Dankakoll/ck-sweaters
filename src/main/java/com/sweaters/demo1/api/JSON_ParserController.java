package com.sweaters.demo1.api;

import com.sweaters.demo1.domain.Items;
import com.sweaters.demo1.domain.Sellers;
import java.util.List;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JSON_ParserController {
    @Autowired
    private final ItemController itemController;
    @Autowired
    private final SellerController sellerController;
    @Autowired
    private final JSON_Parser json_parser;

    JSON_ParserController(ItemController itemController, SellerController sellerController, JSON_Parser json_parser) {
        this.itemController = itemController;
        this.sellerController = sellerController;
        this.json_parser = json_parser;
    }

    @PostMapping(
            value = {"/upload"},
            consumes = {"application/json"}
    )
    ResponseEntity<?> uploadData(@RequestBody String jsonArray) {
        List<JSONArray> Ans = this.json_parser.toData(jsonArray);
        JSONArray ResponseSellers = Ans.get(0);
        JSONArray ResponseItems = Ans.get(1);

        for(int i = 0; i < ResponseItems.length(); ++i) {
            this.itemController.new_item(new Items(ResponseItems.getJSONObject(i)));
            this.sellerController.new_seller(new Sellers(ResponseSellers.getJSONObject(i)));
        }

        return ResponseEntity.noContent().build();
    }
}