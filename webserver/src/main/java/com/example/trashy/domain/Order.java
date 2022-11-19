package com.example.trashy.domain;


import javax.persistence.*;

@Entity
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    private String type;
    private int price;
    private int quantity;

    // 3 Can Type: HIGH, MEDIUM, LOW
    private String canType;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    public Order() {

    }

    //constructor with all parameters
    public Order(Long id, String type, int price, int quantity, String canType) {
        this.id = id;
        this.type = type;
        this.price = price;
        this.quantity = quantity;
        this.canType = canType;
    }

    public Order(Long id, String type) {
        this.id = id;
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public int getPrice() {
        return this.price;
    }

    public String getCanType() {
        return this.canType;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setCanType(String canType) {
        this.canType = canType;
    }
}
