package com.example.ceybank.services;

import com.example.ceybank.models.Beverage;
import com.example.ceybank.repositories.BeverageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeverageService {

    private final BeverageRepository beverageRepository;

    public BeverageService(BeverageRepository beverageRepository) {
        this.beverageRepository = beverageRepository;
    }

    public Beverage createBeverage(Beverage beverage) {
        return beverageRepository.save(beverage);
    }

    public List<Beverage> getAllBeverages() {
        return beverageRepository.findAll();
    }

    public Beverage getBeverageById(Long id) {
        return beverageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Beverage not found with id: " + id));
    }

    public Beverage updateBeverage(Long id, Beverage updated) {
        Beverage beverage = getBeverageById(id);
        beverage.setCode(updated.getCode());
        beverage.setName(updated.getName());
        beverage.setImage(updated.getImage());
        beverage.setDescription(updated.getDescription());
        beverage.setSlug(updated.getSlug());
        beverage.setPrice(updated.getPrice());
        beverage.setPortionType(updated.getPortionType());
        beverage.setItemCategory(updated.getItemCategory());
        beverage.setAvailableForMeals(updated.getAvailableForMeals());
        return beverageRepository.save(beverage);
    }

    public void deleteBeverage(Long id) {
        beverageRepository.deleteById(id);
    }
}
