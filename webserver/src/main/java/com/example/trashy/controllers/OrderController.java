package com.example.trashy.controllers;


import com.example.trashy.domain.Order;
import com.example.trashy.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("orders")
public class OrderController {

    private final OrderService orderService;


    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public void newOrder(@RequestBody Order order) {
        orderService.addOrder(order);
    }



    @DeleteMapping
    public void changeOrder(@RequestBody Order order){
        orderService.deleteOrder(order);
    }



}
