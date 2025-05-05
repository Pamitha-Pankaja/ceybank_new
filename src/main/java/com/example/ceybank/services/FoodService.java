package com.example.ceybank.services;

import com.example.ceybank.models.Food;
import com.example.ceybank.repositories.FoodRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodService {

    private final FoodRepository foodRepository;

    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    public Food createFood(Food food) {
        return foodRepository.save(food);
    }

    public List<Food> getAllFoods() {
        return foodRepository.findAll();
    }

    public Food getFoodById(Long id) {
        return foodRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Food not found with id: " + id));
    }

    public Food updateFood(Long id, Food updated) {
        Food food = getFoodById(id);
        food.setCode(updated.getCode());
        food.setName(updated.getName());
        food.setImage(updated.getImage());
        food.setDescription(updated.getDescription());
        food.setSlug(updated.getSlug());
        food.setPrice(updated.getPrice());
        food.setPortionType(updated.getPortionType());
        food.setItemCategory(updated.getItemCategory());
        food.setAvailableForMeals(updated.getAvailableForMeals());
        return foodRepository.save(food);
    }

    public void deleteFood(Long id) {
        foodRepository.deleteById(id);
    }
}
