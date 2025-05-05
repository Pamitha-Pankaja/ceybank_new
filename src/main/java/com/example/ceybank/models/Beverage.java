package com.example.ceybank.models;

import jakarta.persistence.*;

import java.util.List;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Beverage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long beverageId;

    private String code;
    private String name;
    private String image;
    private String description;
    private String slug;
    private double price;

    // NEW attributes
    private String portionType; // e.g., glass, bottle
    private String itemCategory;    // e.g., Juice, Soft Drink, Alcohol

    @ElementCollection
    @CollectionTable(name = "beverage_meals", joinColumns = @JoinColumn(name = "beverage_id"))
    @Column(name = "meal")
    private List<String> availableForMeals; // e.g., ["BREAKFAST", "DINNER"]

    // Getters and Setters
    public Long getBeverageId() {
        return beverageId;
    }

    public void setBeverageId(Long beverageId) {
        this.beverageId = beverageId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPortionType() {
        return portionType;
    }

    public void setPortionType(String portionType) {
        this.portionType = portionType;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    public List<String> getAvailableForMeals() {
        return availableForMeals;
    }

    public void setAvailableForMeals(List<String> availableForMeals) {
        this.availableForMeals = availableForMeals;
    }
}


