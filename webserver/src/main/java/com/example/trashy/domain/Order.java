package com.example.trashy.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    private String type;


    public Order() {
        this.id = id;
        this.type = type;
    }

    public Order(Long id, String type) {
        this.id = id;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
