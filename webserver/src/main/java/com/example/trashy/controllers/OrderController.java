package com.example.trashy.controllers;


import com.example.trashy.domain.Order;
import com.example.trashy.domain.User;
import com.example.trashy.services.OrderService;
import com.example.trashy.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("orders")
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;


    @Autowired
    public OrderController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @PostMapping
    public void newOrder(@RequestBody Order order) {
        orderService.addOrder(order);
    }


    @GetMapping("/user/{id}")
    public ResponseEntity<List<Order>> getOrdersByUser(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(orderService.getOrdersByUser(user.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping(path = "{orderId}")
    public void deleteOrder(@PathVariable("orderId") Long orderId){
        orderService.deleteOrder(orderId);
    }



}
