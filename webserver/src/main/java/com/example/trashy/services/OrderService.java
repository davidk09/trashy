package com.example.trashy.services;


import com.example.trashy.domain.Order;
import com.example.trashy.domain.User;
import com.example.trashy.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final MatchService matchService;


    @Autowired
    public OrderService(OrderRepository orderRepository, MatchService matchService) {
        this.orderRepository = orderRepository;
        this.matchService = matchService;
    }


    public void addOrder(Order order){
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }

        //Check if existing order from same user exists
        Optional<Order> existingOrderOptional = orderRepository.findOrderByUserAndPriceAndCanType(order.getUser(), order.getPrice(), order.getCanType());
        if (existingOrderOptional.isPresent()) {
            Order existingOrder = existingOrderOptional.get();
            //check if type is the same, if yes add, if not substract and if result negative change type, if result 0 delete order
            if (existingOrder.getType().equals(order.getType())) {
                existingOrder.setQuantity(existingOrder.getQuantity() + order.getQuantity());
                orderRepository.save(existingOrder);
            } else {
                int newQuantity = existingOrder.getQuantity() - order.getQuantity();
                if (newQuantity > 0) {
                    existingOrder.setQuantity(newQuantity);
                    orderRepository.save(existingOrder);
                } else if (newQuantity < 0) {
                    existingOrder.setQuantity(-newQuantity);
                    existingOrder.setType(order.getType());
                    orderRepository.save(existingOrder);
                } else {
                    orderRepository.delete(existingOrder);
                }
            }
        }


        String orderType = order.getType();
        String searchType;
        if (orderType.equals("BUY")) {
            searchType = "SELL";
        } else {
            searchType = "BUY";
        }

        //Check if there is a matching order and update it
        List<Order> matchList = orderRepository.findAllByPriceAndCanTypeAndType(order.getPrice(), order.getCanType(), searchType);

        for (Order matchingOrder : matchList) {

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
                orderRepository.save(order);
                orderRepository.delete(matchingOrder);

                //save the match
                matchService.addMatch(seller, buyer, matchingOrder.getQuantity(), order.getPrice(), order.getCanType());
            } else {
                //if the matching order has the same quantity as the new order, delete both
                orderRepository.delete(matchingOrder);

                //save the match
                matchService.addMatch(seller, buyer, matchingOrder.getQuantity(), order.getPrice(), order.getCanType());
                break;
            }

        }

    }


    /*
    not supposed to actually delete an Order,
    but to potentially reduce/change amount/price of Order
     */
    public void deleteOrder(Order order){

    }

    public List<Order> getOrdersFromUser(User user){
        return orderRepository.findAllByUser(user);
    }








}
