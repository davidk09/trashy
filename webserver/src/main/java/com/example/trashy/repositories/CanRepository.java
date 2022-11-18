package com.example.trashy.repositories;


import com.example.trashy.domain.Can;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CanRepository extends JpaRepository<Can,Long> {
}
