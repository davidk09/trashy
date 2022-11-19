package com.example.trashy.controllers;

import com.example.trashy.domain.Can;
import com.example.trashy.services.CanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000/")
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

    @DeleteMapping
    public ResponseEntity<?> deleteCan(@RequestBody Can can){
        if (canService.deleteCan(can)){
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
