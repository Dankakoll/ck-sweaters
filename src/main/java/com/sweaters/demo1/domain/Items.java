
package com.sweaters.demo1.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import org.json.JSONObject;

@Entity
@Table(name = "items")
public class Items {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long items_id;
    private String items_name;
    //В целях текущей работоспособности цена имеет тип String, в дальнейшем нужно будет разделить
    //саму цену и тип валюты, в зависимости конечно же от вида полученных данных
    // В данном таблице будет показываться текущая цена
    private String price;
    private String origin;
    private Long origin_id;
    private Date updated_at;
    private Date created_at;

    public Items() {
    }

    public Items(String items_name, String price, String origin, Long origin_id) {
        this.items_name = items_name;
        this.price = price;
        Calendar cal = Calendar.getInstance();
        this.origin = origin;
        this.origin_id = origin_id;
        this.updated_at = cal.getTime();
        this.created_at = cal.getTime();
    }

    // Конструктор с использованием данных парсера JSON
    public Items(JSONObject jsonObject) {
        this.items_name = jsonObject.getString("items_name");
        this.price = jsonObject.getString("price");
        this.origin = jsonObject.getString("origin");
        this.origin_id = jsonObject.getLong("origin_id");
        Calendar cal = Calendar.getInstance();
        this.updated_at = cal.getTime();
        this.created_at = cal.getTime();
    }

    public Long getId() {
        return this.items_id;
    }

    public String getItems_name() {
        return this.items_name;
    }

    public String getPrice() {
        return this.price;
    }

    public String getOrigin() {
        return this.origin;
    }

    public Long getOrigin_id() {
        return this.origin_id;
    }

    public Date getUpdated_at() {
        return this.updated_at;
    }

    public Date getCreated_at() {
        return this.created_at;
    }

    public void setId(Long id) {
        this.items_id = id;
    }

    public void setItems_name(String items_name) {
        this.items_name = items_name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setOrigin_id(Long origin_id) {
        this.origin_id = origin_id;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            Items items = (Items)o;
            return this.items_id.equals(items.items_id) && this.items_name.equals(items.items_name) && this.price.equals(items.price) && this.origin.equals(items.origin) && this.origin_id.equals(items.origin_id) && this.updated_at.equals(items.updated_at) && this.created_at.equals(items.created_at);
        } else {
            return false;
        }
    }
    @Override
    public int hashCode() {
        return Objects.hash(new Object[]{this.items_id, this.items_name, this.price, this.origin, this.origin_id, this.updated_at, this.created_at});
    }
}
