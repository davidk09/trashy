package com.example.trashy.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Can {

    @Id
    @GeneratedValue
    private Long id;

    public Can() {
    }

    public Can(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
