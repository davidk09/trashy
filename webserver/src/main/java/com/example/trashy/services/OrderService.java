package com.example.trashy.services;


import com.example.trashy.domain.ExchangeOrder;
import com.example.trashy.domain.User;
import com.example.trashy.repositories.OrderRepository;
import com.example.trashy.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private final UserRepository userRepository;
    private final MatchService matchService;


    @Autowired
    public OrderService(OrderRepository orderRepository,UserRepository userRepository, MatchService matchService) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.matchService = matchService;
    }


    public void addOrder(ExchangeOrder order, Optional<User> optionalUser){
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }

        if(optionalUser.isEmpty()){
            throw new IllegalArgumentException("The User does not exist (Custom)");
        }
        User user = optionalUser.get();
        Set<ExchangeOrder> existingOrders = user.getOrders();

        //kann man schöner machen
        Optional<ExchangeOrder> existingOrderOptional = existingOrders.stream().filter(o -> o.getCanType().equals(order.getCanType())).findAny();



        //Check if existing order from same user exists

        //Optional<ExchangeOrder> existingOrderOptional = orderRepository.findOrderByUserAndPriceAndCanType(order.getUser(), order.getPrice(), order.getCanType());
                //orderRepository.findAllByPriceAndCanTypeAndType(price,order.getCanType(),order.getType());
        //orderRepository.findOrderByUserAndPriceAndCanType(order.getUser(), order.getPrice(), order.getCanType());
        //orderRepository.findAllByPriceAndCanTypeAndType()
        if (existingOrderOptional.isPresent()) {
            ExchangeOrder existingOrder = existingOrderOptional.get();
            //check if type is the same, if yes add, if not substract and if result negative change type, if result 0 delete order
            if (existingOrder.getType().equals(order.getType())) {
                existingOrder.setQuantity(existingOrder.getQuantity() + order.getQuantity());
                addOrderByUser(existingOrder,optionalUser);
            } else {
                int newQuantity = existingOrder.getQuantity() - order.getQuantity();
                if (newQuantity > 0) {
                    existingOrder.setQuantity(newQuantity);
                    addOrderByUser(existingOrder,optionalUser);
                } else if (newQuantity < 0) {
                    existingOrder.setQuantity(-newQuantity);
                    existingOrder.setType(order.getType());
                    addOrderByUser(existingOrder,optionalUser);
                } else {
                    removeOrder(existingOrder);
                }
            }
        }


        String orderType = order.getType();
        String searchType;
        if (orderType.equals("BUY")) {
            searchType = "SELL";
            List<ExchangeOrder> matchList_ = orderRepository.findAll().stream().filter(ord -> ord.getPrice() <= order.getPrice() && ord.getCanType().equals(order.getCanType()) && ord.getType().equals(searchType)).toList();
        } else {
            searchType = "BUY";
            List<ExchangeOrder> matchList_ = orderRepository.findAll().stream().filter(ord -> ord.getPrice() >= order.getPrice() && ord.getCanType().equals(order.getCanType()) && ord.getType().equals(searchType)).toList();
        }

        //Check if there is a matching order and update it
        List<ExchangeOrder> matchList = orderRepository.findAllByPriceAndCanTypeAndType(order.getPrice(), order.getCanType(), searchType);


        for (ExchangeOrder matchingOrder : matchList) {

            //determine seller and buyer
            User seller;
            User buyer;
            if (order.getType().equals("BUY")) {
                seller = matchingOrder.getUser();
                buyer = order.getUser();
            } else {
                seller = order.getUser();
                buyer = matchingOrder.getUser();
            }


            if (matchingOrder.getQuantity() > order.getQuantity()) {
                //if the matching order has more quantity than the new order, update the matching order and don't add the new order
                matchingOrder.setQuantity(matchingOrder.getQuantity() - order.getQuantity());
                orderRepository.save(matchingOrder);


                //save the match
                matchService.addMatch(seller, buyer, order.getQuantity(), order.getPrice(), order.getCanType());
                break;

            } else if (matchingOrder.getQuantity() < order.getQuantity()) {
                //if the matching order has less quantity than the new order, update the new order and delete the matching order
                order.setQuantity(order.getQuantity() - matchingOrder.getQuantity());
                addOrderByUser(order,optionalUser);
                removeOrder(matchingOrder);

                //save the match
                matchService.addMatch(seller, buyer, matchingOrder.getQuantity(), order.getPrice(), order.getCanType());
            } else {
                //if the matching order has the same quantity as the new order, delete both
                removeOrder(matchingOrder);

                //save the match
                matchService.addMatch(seller, buyer, matchingOrder.getQuantity(), order.getPrice(), order.getCanType());
                break;
            }

        }


    }


    public void addOrderByUser(ExchangeOrder order1, Optional<User> optionalUser){
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            ExchangeOrder order = orderRepository.save(order1);

            order.setUser(user);
            orderRepository.save(order);
            user.addOrder(order);
            userRepository.save(user);
        }
    }



    public void removeOrder(ExchangeOrder order){
        User u = order.getUser();
        u.removeOrder(order);
        userRepository.save(u);
        orderRepository.delete(order);
    }


    /*
    not supposed to actually delete an Order,
    but to potentially reduce/change amount/price of Order
     */
    public void deleteOrder(Long orderId) {
        var order =orderRepository.findById(orderId);
        if(order.isPresent()){
            removeOrder(order.get());
        }else {
            throw new IllegalArgumentException("Order does not exist");
        }


    }

    public List<ExchangeOrder> getOrdersByUser(User user){
        return orderRepository.findAllByUser(user);
    }









}
