package com.example.trashy.domain;


import javax.persistence.*;

@Entity
public class Can {

    @Id
    @GeneratedValue
    private Long id;
    private CanType type;
    private double latitude;
    private double longitude;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Can() {
    }

    public Can(Long id) {
        this.id = id;
    }

    public Can(CanType type, double latitude, double longitude, User owner) {
        this.type = type;
        this.latitude = latitude;
        this.longitude = longitude;
        this.owner = owner;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CanType getType() {
        return type;
    }

    public void setType(CanType type) {
        this.type = type;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
