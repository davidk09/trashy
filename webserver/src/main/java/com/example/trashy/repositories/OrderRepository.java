package com.example.trashy.repositories;


import com.example.trashy.domain.Order;
import com.example.trashy.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findOrderByPriceAndCanTypeAndType(double price, String canType, String type);
    Optional<Order> findOrderByUserAndPriceAndCanType(User user, double price, String canType);
    List<Order> findAllByPriceAndCanTypeAndType(double price, String canType, String type);
    List<Order> findAllByUser(User user);



}
