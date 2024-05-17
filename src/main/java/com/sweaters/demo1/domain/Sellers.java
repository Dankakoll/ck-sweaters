package com.sweaters.demo1.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;
import org.json.JSONObject;

@Entity
@Table(
        name = "sellers"
)
public class Sellers {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long seller_id;
    private String seller_name;
    private String origin;
    private Long origin_id;
    // Привести к дробному типу, из-за разницы отображений таких данных в разных маркетплейсах
    // пока что было решено оставить в таком виде
    private String origin_rate;

    public Sellers() {
    }

    public Sellers(String seller_name, String origin, Long origin_id, String origin_rate) {
        this.seller_name = seller_name;
        this.origin = origin;
        this.origin_id = origin_id;
        this.origin_rate = origin_rate;
    }

    // Конструктор с использованием данных парсера JSON
    public Sellers(JSONObject jsonObject) {
        this.seller_name = jsonObject.getString("seller_name");
        this.origin = jsonObject.getString("origin");
        this.origin_rate = jsonObject.getString("origin_rate");
        this.origin_id = jsonObject.getLong("origin_id");
    }

    public Long getId() {
        return this.seller_id;
    }

    public String getSeller_name() {
        return this.seller_name;
    }

    public String getOrigin() {
        return this.origin;
    }

    public Long getOrigin_id() {
        return this.origin_id;
    }

    public String getOrigin_rate() {
        return this.origin_rate;
    }

    public void setSeller_name(String seller_name) {
        this.seller_name = seller_name;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setOrigin_id(Long origin_id) {
        this.origin_id = origin_id;
    }

    public void setOrigin_rate(String origin_rate) {
        this.origin_rate = origin_rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            Sellers sellers = (Sellers)o;
            return this.seller_id.equals(sellers.seller_id) && Objects.equals(this.seller_name, sellers.seller_name) && Objects.equals(this.origin, sellers.origin) && Objects.equals(this.origin_id, sellers.origin_id) && Objects.equals(this.origin_rate, sellers.origin_rate);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(new Object[]{this.seller_id, this.seller_name, this.origin, this.origin_id, this.origin_rate});
    }
}