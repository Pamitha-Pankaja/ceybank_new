package com.example.ceybank.repositories;

import com.example.ceybank.models.FoodBillItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodBillItemRepository extends JpaRepository<FoodBillItem, Long> {
}
