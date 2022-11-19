package com.example.trashy.domain;

import javax.persistence.*;

@Entity
public class OrderMatch {

    //Keep track of the matches
    @Id
    @GeneratedValue
    private Long id;
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private User seller;

    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private User buyer;

    private int price;

    private String canType;


    public OrderMatch() {
    }

    public OrderMatch(User seller, User buyer, int price, int quantity, String canType) {
        this.quantity = quantity;
        this.seller = seller;
        this.buyer = buyer;
        this.price = price;
        this.canType = canType;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }


}
