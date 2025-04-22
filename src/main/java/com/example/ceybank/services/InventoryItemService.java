package com.example.ceybank.services;

import com.example.ceybank.models.InventoryItem;
import com.example.ceybank.repositories.InventoryItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryItemService {

    @Autowired
    InventoryItemRepository inventoryItemRepository;

    public InventoryItem addInventoryItems(InventoryItem inventoryItem) {
        return inventoryItemRepository.save(inventoryItem);
    }
}
