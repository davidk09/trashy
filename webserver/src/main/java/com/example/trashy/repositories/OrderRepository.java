package com.example.trashy.repositories;


import com.example.trashy.domain.ExchangeOrder;
import com.example.trashy.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface OrderRepository extends JpaRepository<ExchangeOrder, Long> {

    Optional<ExchangeOrder> findOrderByPriceAndCanTypeAndType(int price, String canType, String type);
    Optional<ExchangeOrder> findOrderByUserAndPriceAndCanType(User user, int price, String canType);

    List<ExchangeOrder> findAllByPriceAndCanTypeAndType(int price, String canType, String type);
    List<ExchangeOrder> findAllByUser(User user);



}
