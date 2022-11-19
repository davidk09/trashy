package com.example.trashy.services;


import com.example.trashy.domain.Order;
import com.example.trashy.domain.User;
import com.example.trashy.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findUserById(id);
    }
    public void addUser(User user){
        userRepository.save(user);
    }



}
