package com.example.trashy.services;


import com.example.trashy.domain.Can;
import com.example.trashy.repositories.CanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CanService {

    private final CanRepository canRepository;

    @Autowired
    public CanService(CanRepository canRepository) {
        this.canRepository = canRepository;
    }


    public List<Can> getAllCans() {
        return canRepository.findAll();
    }


    public boolean addCan(Can can) {
        if (canRepository.findCanById(can.getId()).isPresent()) {
            return false;
        } else {
            canRepository.save(can);
            return true;
        }
    }
}
