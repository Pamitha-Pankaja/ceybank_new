package com.example.ceybank.controllers;

import com.example.ceybank.models.Beverage;
import com.example.ceybank.services.BeverageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/beverages")
public class BeverageController {

    private final BeverageService beverageService;

    public BeverageController(BeverageService beverageService) {
        this.beverageService = beverageService;
    }

    @PostMapping
    public ResponseEntity<Beverage> create(@RequestBody Beverage beverage) {
        return ResponseEntity.ok(beverageService.createBeverage(beverage));
    }

    @GetMapping
    public ResponseEntity<List<Beverage>> getAll() {
        return ResponseEntity.ok(beverageService.getAllBeverages());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Beverage> getById(@PathVariable Long id) {
        return ResponseEntity.ok(beverageService.getBeverageById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Beverage> update(@PathVariable Long id, @RequestBody Beverage beverage) {
        return ResponseEntity.ok(beverageService.updateBeverage(id, beverage));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        beverageService.deleteBeverage(id);
        return ResponseEntity.noContent().build();
    }
}

