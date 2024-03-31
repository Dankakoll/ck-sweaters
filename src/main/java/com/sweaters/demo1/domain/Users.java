package com.sweaters.demo1.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "users")
public class Users {
    private @Id @GeneratedValue Long id;
    private String login;
    private String password;
    private String email;

    public Users(){}

    public Users (String login, String password, String email)
    {
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public Long getId() {
        return this.id;
    }
    public String getLogin(){
        return this.login;
    }

    public String getPassword() {
        return this.password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return id.equals(users.id) && Objects.equals(login, users.login) && Objects.equals(password, users.password) && Objects.equals(email, users.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, email);
    }
}
