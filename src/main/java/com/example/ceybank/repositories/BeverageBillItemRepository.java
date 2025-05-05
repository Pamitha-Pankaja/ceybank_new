package com.example.ceybank.repositories;

import com.example.ceybank.models.BeverageBillItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeverageBillItemRepository extends JpaRepository<BeverageBillItem, Long> {
}
