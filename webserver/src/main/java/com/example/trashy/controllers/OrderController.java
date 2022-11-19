package com.example.trashy.controllers;


import com.example.trashy.domain.ExchangeOrder;
import com.example.trashy.domain.User;
import com.example.trashy.requests.OrderRequest;
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
    public void newOrder(@RequestBody OrderRequest orderRequest) {
        Optional<User> optionalUser = userService.getUserById(orderRequest.getUserId());
        ExchangeOrder order = orderRequest.getOrder();
        System.out.println("Got Order" + order.toString());
        System.out.println("Request: ID is " + orderRequest.getUserId() + " -----------------------------------------------------------------------\n\n\n");
        orderService.addOrder(order,optionalUser);
    }


    @GetMapping("user")
    public User getAUser(@RequestParam Long userId ){
        var user = userService.getUserById(userId);
        return user.get();
    }


    @GetMapping("/user/{id}")
    public ResponseEntity<List<ExchangeOrder>> getOrdersByUser(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(orderService.getOrdersByUser(user.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping
    public OrderRequest getDummyOrder(){
        var order =new ExchangeOrder(1L,"BUY",12,100,"high");
        var req = new OrderRequest(2L,order);
        return req;
    }


    @DeleteMapping(path = "{orderId}")
    public void deleteOrder(@PathVariable("orderId") Long orderId){
        orderService.deleteOrder(orderId);
    }



}
