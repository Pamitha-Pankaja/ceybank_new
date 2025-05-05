package com.example.ceybank.repositories;

import com.example.ceybank.models.Beverage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BeverageRepository extends JpaRepository<Beverage, Long> {
    Optional<Beverage> findByCode(String code);
}

