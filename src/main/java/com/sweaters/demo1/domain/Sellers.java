package com.sweaters.demo1.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "sellers")
public class Sellers {

    private @Id @GeneratedValue  Long id;
    private String seller_name;
    private String origin;
    private Long  origin_rate;

    public Sellers(){}

    public Sellers(String seller_name, String origin, Long origin_rate)
    {
        this.seller_name=seller_name;
        this.origin=origin;
        this.origin_rate=origin_rate;
    }

    public Long getId() {
        return this.id;
    }

    public String getSeller_name() {
        return this.seller_name;
    }

    public String getOrigin() {
        return this.origin;
    }

    public Long getOrigin_rate() {
        return this.origin_rate;
    }

    public void setSeller_name(String seller_name)
    {
        this.seller_name=seller_name;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setOrigin_rate(Long origin_rate) {
        this.origin_rate = origin_rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sellers sellers = (Sellers) o;
        return id.equals(sellers.id) && Objects.equals(seller_name, sellers.seller_name) && Objects.equals(origin, sellers.origin) && Objects.equals(origin_rate, sellers.origin_rate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, seller_name, origin, origin_rate);
    }
}
