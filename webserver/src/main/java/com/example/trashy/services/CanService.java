package com.example.trashy.services;


import com.example.trashy.repositories.CanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CanService {

    private final CanRepository canRepository;

    @Autowired
    public CanService(CanRepository canRepository) {
        this.canRepository = canRepository;
    }






}
