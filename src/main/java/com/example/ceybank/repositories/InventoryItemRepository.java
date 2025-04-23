package com.example.ceybank.repositories;

import com.example.ceybank.models.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryItemRepository extends JpaRepository<InventoryItem, Integer> {
    Optional<InventoryItem> findByItemCode(String itemCode);
}
