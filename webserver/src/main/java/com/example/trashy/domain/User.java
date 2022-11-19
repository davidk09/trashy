package com.example.trashy.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User {


    @Id
    @GeneratedValue
    private Long id;


    private String name;



    @OneToMany
    private Set<ExchangeOrder> orders = new HashSet<>();

    public User() {
    }

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }


    public void addOrder(ExchangeOrder order){
        this.orders.add(order);
    }


    public void removeOrder(ExchangeOrder order){
        this.orders.remove(order);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ExchangeOrder> getOrders() {
        return orders;
    }

    public void setOrders(Set<ExchangeOrder> order) {
        this.orders = order;
    }
}
