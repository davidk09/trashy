package com.example.trashy.services;


import com.example.trashy.domain.Order;
import com.example.trashy.repositories.CanRepository;
import com.example.trashy.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;


    @Autowired
    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }


    public void addOrder(Order order){
        orderRepository.save(order);
    }

    /*
    not supposed to actually delete an Order,
    but to potentially reduce/change amount/price of Order
     */
    public void deleteOrder(Order order){

    }




}
