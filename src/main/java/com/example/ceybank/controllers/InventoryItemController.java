package com.example.ceybank.controllers;

import com.example.ceybank.models.InventoryItem;
import com.example.ceybank.services.InventoryItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/InventoryItem")
public class InventoryItemController {

    @Autowired
    InventoryItemService inventoryItemService;

    @PostMapping("/add")
    public ResponseEntity<InventoryItem> addInventoryItems(@RequestBody InventoryItem inventoryItem) {
        inventoryItemService.addInventoryItems(inventoryItem);
        return ResponseEntity.ok(inventoryItem);
    }

}
