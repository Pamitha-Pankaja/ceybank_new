package com.example.ceybank.controllers;

import com.example.ceybank.responses.BeverageOrderRequest;
import com.example.ceybank.services.BeverageOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/beverage-orders")
public class BeverageOrderController {

    @Autowired
    private BeverageOrderService beverageOrderService;

    @PostMapping("/place")
    public ResponseEntity<String> placeBeverageOrder(@RequestBody BeverageOrderRequest request) {
        try {
            beverageOrderService.placeBeverageOrder(request);
            return ResponseEntity.ok("Beverage order placed successfully.");
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body("Error placing beverage order: " + ex.getMessage());
        }
    }
}
