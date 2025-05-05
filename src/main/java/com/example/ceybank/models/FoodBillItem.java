package com.example.ceybank.models;

import jakarta.persistence.*;

import jakarta.persistence.*;

@Entity
public class FoodBillItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "food_id")
    private Food food;

    private int portions;
    private double total; // item.price * portions

    @ManyToOne
    @JoinColumn(name = "food_bill_id")
    private FoodBill foodBill;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public int getPortions() {
        return portions;
    }

    public void setPortions(int portions) {
        this.portions = portions;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public FoodBill getFoodBill() {
        return foodBill;
    }

    public void setFoodBill(FoodBill foodBill) {
        this.foodBill = foodBill;
    }
}


