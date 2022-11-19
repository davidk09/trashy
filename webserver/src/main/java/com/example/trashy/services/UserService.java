package com.example.trashy.services;


import com.example.trashy.domain.ExchangeOrder;
import com.example.trashy.domain.User;
import com.example.trashy.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final OrderService orderService;

    @Autowired
    public UserService(UserRepository userRepository, OrderService orderService) {
        this.userRepository = userRepository;
        this.orderService = orderService;
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

    public boolean deleteUser(User user){
        Optional<User> userToDeleteOptional = userRepository.findUserById(user.getId());
        if (userToDeleteOptional.isPresent()){
            User userToDelete = userToDeleteOptional.get();
            for (ExchangeOrder order : userToDelete.getOrders()){
                orderService.deleteOrder(order.getId());
            }
            userRepository.delete(user);
            return true;
        } else {
            return false;
        }
    }


}
