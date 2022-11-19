package com.example.trashy.services;


import com.example.trashy.domain.Order;
import com.example.trashy.domain.User;
import com.example.trashy.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public boolean addUser(User user){
        if (userRepository.findUserById(user.getId()).isPresent()){
            return false;
        } else {
            userRepository.save(user);
            return true;
        }
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }



}
