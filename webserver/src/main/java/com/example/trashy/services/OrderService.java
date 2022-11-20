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
        ExchangeOrder finalOrder = order;
        Optional<ExchangeOrder> existingOrderOptional = existingOrders.stream().filter(o -> o.getCanType().equals(finalOrder.getCanType()) && o.getPrice() == finalOrder.getPrice()).findAny();

        if (existingOrderOptional.isPresent()) {
            ExchangeOrder existingOrder = existingOrderOptional.get();
            //check if type is the same, if yes add, if not substract and if result negative change type, if result 0 delete order
            if (existingOrder.getType().equals(order.getType())) {
                existingOrder.setQuantity(existingOrder.getQuantity() + order.getQuantity());
                addOrderByUser(existingOrder,optionalUser);
                order = existingOrder;
            } else {
                int newQuantity = existingOrder.getQuantity() - order.getQuantity();
                if (newQuantity > 0) {
                    existingOrder.setQuantity(newQuantity);
                    addOrderByUser(existingOrder,optionalUser);
                    order = existingOrder;
                } else if (newQuantity < 0) {
                    existingOrder.setQuantity(-newQuantity);
                    existingOrder.setType(order.getType());
                    addOrderByUser(existingOrder,optionalUser);
                    order = existingOrder;
                } else {
                    removeOrder(existingOrder);
                    return;
                }
            }
        }

        String orderType = order.getType();
        String searchType;
        List<ExchangeOrder> matchList;
        if (orderType.equals("BUY")) {
            //order wants to buy
            //matched Order wants to sell
            searchType = "SELL";
            ExchangeOrder finalOrder1 = order;
            matchList = orderRepository.findAll().stream().filter(ord -> ord.getPrice() <= finalOrder1.getPrice() && ord.getCanType().equals(finalOrder1.getCanType()) && ord.getType().equals(searchType)).toList();
            if(matchList.isEmpty()){
                addOrderByUser(order,user);
            }
            for(ExchangeOrder matchingOrder : matchList) {
                if (matchOrders(user, matchingOrder.getUser(), matchingOrder, order, user))
                        break;

            }
        } else {
            searchType = "BUY";
            ExchangeOrder finalOrder2 = order;
            matchList = orderRepository.findAll().stream().filter(ord -> ord.getPrice() >= finalOrder2.getPrice() && ord.getCanType().equals(finalOrder2.getCanType()) && ord.getType().equals(searchType)).toList();
            if(matchList.isEmpty()){
                addOrderByUser(order,user);
            }
            for(ExchangeOrder matchingOrder : matchList) {
                    if (matchOrders(matchingOrder.getUser(), user, matchingOrder, order, user))
                        break;


            }
        }

    }


    public boolean  matchOrders(User buyer,User seller,ExchangeOrder matchingOrder, ExchangeOrder order,User user){
        if (matchingOrder.getQuantity() > order.getQuantity()) {
            //if the matching order has more quantity than the new order, update the matching order and don't add the new order
            matchingOrder.setQuantity(matchingOrder.getQuantity() - order.getQuantity());
            addOrderByUser(matchingOrder,matchingOrder.getUser());

            //save the match
            matchService.addMatch(seller, buyer, order.getQuantity(), order.getPrice(), order.getCanType());
            return true;
        } else if (matchingOrder.getQuantity() < order.getQuantity()) {
            //if the matching order has less quantity than the new order, update the new order and delete the matching order
            order.setQuantity(order.getQuantity() - matchingOrder.getQuantity());
            addOrderByUser(order,user);
            removeOrder(matchingOrder);

            //save the match
            matchService.addMatch(seller, buyer, matchingOrder.getQuantity(), order.getPrice(), order.getCanType());
            return false;
        } else {
            //if the matching order has the same quantity as the new order, delete both
            removeOrder(matchingOrder);
            removeOrder(order);

            //save the match
            matchService.addMatch(seller, buyer, matchingOrder.getQuantity(), order.getPrice(), order.getCanType());
            return true;
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

    public void addOrderByUser(ExchangeOrder order1, User user){
            ExchangeOrder order = orderRepository.save(order1);
            order.setUser(user);
            orderRepository.save(order);
            user.addOrder(order);
            userRepository.save(user);
    }

    public void removeOrder(ExchangeOrder order){
        User u = order.getUser();
        if(u != null){
            u.removeOrder(order);
            userRepository.save(u);
        }
        orderRepository.delete(order);
    }


    /*
    not supposed to actually delete an Order,
    but to potentially reduce/change amount/price of Order
     */
    public boolean deleteOrder(Long orderId) {
        Optional<ExchangeOrder> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isEmpty()) {
            return false;
        }
        ExchangeOrder order = orderOptional.get();
        User user = order.getUser();
        user.getOrders().remove(order);
        userRepository.save(user);
        orderRepository.delete(order);
        return true;
    }

    public List<ExchangeOrder> getOrdersByUser(User user){
        return orderRepository.findAllByUser(user);
    }

}
