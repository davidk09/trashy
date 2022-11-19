package com.example.trashy.controllers;

import com.example.trashy.domain.Can;
import com.example.trashy.services.CanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("cans")
public class CanController {


    private final CanService canService;

    @Autowired
    public CanController(CanService canService) {
        this.canService = canService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllCans(){
        return ResponseEntity.ok(canService.getAllCans());
    }


    @PostMapping("/add")
    public ResponseEntity<?> addNewCan(@RequestBody Can can){
        if (canService.addCan(can)){
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
