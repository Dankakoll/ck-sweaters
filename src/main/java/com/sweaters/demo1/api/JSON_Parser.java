
package com.sweaters.demo1.api;

import java.util.Arrays;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class JSON_Parser {
    public JSON_Parser()
    {
    }

    List<JSONArray> toData(String jsonObject) {
        JSONArray OG_JSON = new JSONArray(jsonObject);
        String[] SellerAtr = new String[]{"seller_name", "origin", "origin_rate", "origin_id"};
        String[] ItemsAtr = new String[]{"items_name", "price", "origin", "origin_id"};
        JSONArray ResponseSeller = new JSONArray();
        JSONArray ResponseItems = new JSONArray();

        for(int i = 0; i < OG_JSON.length(); ++i) {
            String[] elems = JSONObject.getNames(OG_JSON.getJSONObject(i));
            JSONObject Seller_dummy = new JSONObject();
            JSONObject Item_dummy = new JSONObject();

            for(int j = 0; j < elems.length; ++j) {
                String column = elems[j];
                String values = OG_JSON.getJSONObject(i).getString(elems[j]);
                if (Arrays.asList(SellerAtr).contains(column)) {
                    Seller_dummy.put(elems[j], values);
                }

                if (Arrays.asList(ItemsAtr).contains(column)) {
                    Item_dummy.put(elems[j], values);
                }
            }

            ResponseItems.put(Item_dummy);
            ResponseSeller.put(Seller_dummy);
        }

        return Arrays.asList(ResponseSeller, ResponseItems);
    }
}
