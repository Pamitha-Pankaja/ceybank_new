package com.example.ceybank.controllers;

import com.example.ceybank.models.InventoryItem;
import com.example.ceybank.responses.InventoryItemResponse;
import com.example.ceybank.services.InventoryItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    // READ ALL
//    @GetMapping("/all")
//    public ResponseEntity<List<InventoryItem>> getAllInventoryItems() {
//        return ResponseEntity.ok(inventoryItemService.getAllInventoryItems());
//    }

    @GetMapping("/all")
    public ResponseEntity<List<InventoryItemResponse>> getAllInventoryItems() {
        List<InventoryItemResponse> responseList = inventoryItemService.getAllInventoryItems();
        return ResponseEntity.ok(responseList);
    }


    // READ ONE BY ID
    @GetMapping("/{id}")
    public ResponseEntity<InventoryItem> getInventoryItemById(@PathVariable Integer id) {
        InventoryItem item = inventoryItemService.getInventoryItemById(id);
        return ResponseEntity.ok(item);
    }

    // UPDATE
    @PutMapping("/update/{id}")
    public ResponseEntity<InventoryItem> updateInventoryItem(@PathVariable Integer id, @RequestBody InventoryItem updatedItem) {
        InventoryItem item = inventoryItemService.updateInventoryItem(id, updatedItem);
        return ResponseEntity.ok(item);
    }

    // DELETE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteInventoryItem(@PathVariable Integer id) {
        inventoryItemService.deleteInventoryItem(id);
        return ResponseEntity.ok("Item deleted successfully.");
    }





}
