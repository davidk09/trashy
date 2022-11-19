package com.example.trashy.repositories;


import com.example.trashy.domain.Can;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CanRepository extends JpaRepository<Can,Long> {
    Optional<Object> findCanById(Long id);
}
