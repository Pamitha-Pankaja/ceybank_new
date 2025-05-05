package com.example.ceybank.services;

import com.example.ceybank.models.InventoryItem;
import com.example.ceybank.repositories.InventoryItemRepository;
import com.example.ceybank.responses.InventoryItemResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class InventoryItemService {

    @Autowired
    InventoryItemRepository inventoryItemRepository;

    public InventoryItem addInventoryItems(InventoryItem inventoryItem) {
//        inventoryItem.setCreatedOn(LocalDateTime.now());
//        inventoryItem.setCreatedBy("admin");
        return inventoryItemRepository.save(inventoryItem);
    }

    // READ ALL
//    public List<InventoryItem> getAllInventoryItems() {
//        return inventoryItemRepository.findAll();
//    }



    // READ BY ID
    public InventoryItem getInventoryItemById(Integer id) {
        return inventoryItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found with ID: " + id));
    }

    // UPDATE
    public InventoryItem updateInventoryItem(Integer id, InventoryItem updatedItem) {
        InventoryItem existing = inventoryItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found with ID: " + id));

        existing.setItemCode(updatedItem.getItemCode());
        existing.setStatus(updatedItem.getStatus());
        existing.setItemName(updatedItem.getItemName());
        existing.setDescription(updatedItem.getDescription());
        existing.setSlug(updatedItem.getSlug());
        existing.setQuantity(updatedItem.getQuantity());
        existing.setUnit(updatedItem.getUnit());
        existing.setCategory(updatedItem.getCategory());
        existing.setReOrderLevel(updatedItem.getReOrderLevel());
        existing.setMaximumReorderLevel(updatedItem.getMaximumReorderLevel());
        existing.setImage(updatedItem.getImage());

//        existing.setUpdatedOn(LocalDateTime.now());
//        existing.setUpdatedBy("admin");

        return inventoryItemRepository.save(existing);
    }

    // DELETE
    public void deleteInventoryItem(Integer id) {
        InventoryItem existing = inventoryItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found with ID: " + id));
        inventoryItemRepository.delete(existing);
    }


    public List<InventoryItemResponse> getAllInventoryItems() {
        List<InventoryItem> items = inventoryItemRepository.findAll();

        return items.stream().map(item -> {
            InventoryItemResponse response = new InventoryItemResponse();
            response.setItemId(item.getItemId());
            response.setItemCode(item.getItemCode());
            response.setItemName(item.getItemName());
            response.setStatus(item.getStatus());
            response.setUnit(item.getUnit());
            response.setCategory(item.getCategory());
            response.setQuantity(item.getQuantity());
            response.setReOrderLevel(item.getReOrderLevel());
            response.setMaximumReorderLevel(item.getMaximumReorderLevel());
            response.setImage(item.getImage());
            response.setDescription(item.getDescription());
            response.setSlug(item.getSlug());
            return response;
        }).toList();
    }

}
