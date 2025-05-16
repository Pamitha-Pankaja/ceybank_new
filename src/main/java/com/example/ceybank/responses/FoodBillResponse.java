package com.example.ceybank.responses;

import java.time.LocalDate;
import java.util.List;

import java.time.LocalDate;
import java.util.List;

public class FoodBillResponse {
    private Long foodBillId;
    private String roomNo;
    private LocalDate date;
    private String mealType;
    private double grandTotal;
    private List<FoodBillItemResponse> foodBillItems;

    // Getters and Setters
    public Long getFoodBillId() {
        return foodBillId;
    }

    public void setFoodBillId(Long foodBillId) {
        this.foodBillId = foodBillId;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public double getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(double grandTotal) {
        this.grandTotal = grandTotal;
    }

    public List<FoodBillItemResponse> getFoodBillItems() {
        return foodBillItems;
    }

    public void setFoodBillItems(List<FoodBillItemResponse> foodBillItems) {
        this.foodBillItems = foodBillItems;
    }

    // Nested static class
    public static class FoodBillItemResponse {
        private Long id;
        private String foodName;
        private int portions;
        private double total;

        // Getters and Setters
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getFoodName() {
            return foodName;
        }

        public void setFoodName(String foodName) {
            this.foodName = foodName;
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
    }
}
