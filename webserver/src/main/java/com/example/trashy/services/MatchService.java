package com.example.trashy.services;

import com.example.trashy.domain.OrderMatch;
import com.example.trashy.domain.User;
import com.example.trashy.repositories.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatchService {

    private final MatchRepository matchRepository;

    @Autowired
    public MatchService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public void addMatch(User seller, User buyer, int quantity, int price, String canType) {
        OrderMatch match = new OrderMatch(seller, buyer, price, quantity, canType);
        matchRepository.save(match);
    }


}
