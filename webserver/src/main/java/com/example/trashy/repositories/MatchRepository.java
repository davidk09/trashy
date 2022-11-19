package com.example.trashy.repositories;

import com.example.trashy.domain.OrderMatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository extends JpaRepository<OrderMatch, Long> {

}

