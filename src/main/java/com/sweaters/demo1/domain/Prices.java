package com.sweaters.demo1.domain;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name="prices")
//Показ всей истории цен для определенного товара.
public class Prices {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long price_id;
    private String price;
    private String origin;
    private Long origin_id;
    private Date updated_at;


    public Prices (String price,String origin, Long origin_id, Date updated_at)
    {
        this.price=price;
        this.origin=origin;
        this.origin_id=origin_id;
        this.updated_at=updated_at;
    }

    public Prices() {

    }


    public Long getId() {
        return price_id;
    }

    public String getPrice() {
        return price;
    }

    public String getOrigin() {
        return origin;
    }

    public Long getOrigin_id() {
        return origin_id;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setOrigin_id(Long origin_id) {
        this.origin_id = origin_id;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price_id, price, origin, origin_id, updated_at);
    }
}
