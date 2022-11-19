package com.example.trashy.controllers;


import com.example.trashy.domain.User;
import com.example.trashy.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping
    public void addNewUser(@RequestBody User user){
        userService.addUser(user);
    }


    @GetMapping
    public User getDummyUser(){
        return new User(1L,"paul");
    }


}
