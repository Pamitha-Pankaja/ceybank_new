package com.example.ceybank.controllers;

import com.example.ceybank.models.FoodBill;
import com.example.ceybank.responses.FoodOrderRequest;
import com.example.ceybank.services.FoodOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/food-orders")
public class FoodOrderController {

    @Autowired
    private FoodOrderService foodOrderService;

    @PostMapping("/place")
    public ResponseEntity<String> placeFoodOrder(@RequestBody FoodOrderRequest request) {
        try {
            foodOrderService.placeFoodOrder(request);
            return ResponseEntity.ok("Food order placed successfully.");
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body("Error placing food order: " + ex.getMessage());
        }
    }
}

