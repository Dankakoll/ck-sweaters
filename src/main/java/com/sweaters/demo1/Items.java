package com.sweaters.demo1;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

@Entity
public class Items {
    private @Id @GeneratedValue Long id;
    private String origin_name;
    private Long price;
    private Date updated_at;

    public Items(){}

    public Items(String origin_name, Long price)
    {
        this.origin_name=origin_name;
        this.price=price;
        Calendar cal= Calendar.getInstance();
        this.updated_at=cal.getTime();
    }


    public Long getId()
    {
        return this.id;
    }
    public String getOrigin_name()
    {
        return this.origin_name;
    }
    public Long getPrice()
    {
        return this.price;
    }
    public Date getUpdated_at() {
        return this.updated_at;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOrigin_name(String origin_name) {
        this.origin_name = origin_name;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Items items = (Items) o;
        return id.equals(items.id) && origin_name.equals(items.origin_name) && price.equals(items.price) && updated_at.equals(items.updated_at);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, origin_name, price, updated_at);
    }

    @Override
    public String toString() {
        return "Items{" +
                "id=" + id +
                ", origin_name='" + origin_name + '\'' +
                ", price=" + price +
                ", updated_at=" + updated_at +
                '}';
    }
}
