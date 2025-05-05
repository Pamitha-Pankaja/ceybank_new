package com.example.ceybank.responses;

import java.time.LocalDate;
import java.util.List;

import java.time.LocalDate;
import java.util.List;

import java.time.LocalDate;
import java.util.List;

public class FoodOrderRequest {
    private String roomNo;
    private Long reservationId;
    private LocalDate orderDate;
    private String mealType;
    private List<FoodOrderItem> items;

    // Getters and Setters
    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public List<FoodOrderItem> getItems() {
        return items;
    }

    public void setItems(List<FoodOrderItem> items) {
        this.items = items;
    }

    // Inner static class
    public static class FoodOrderItem {
        private Long foodId;
        private int portions;

        // Getters and Setters
        public Long getFoodId() {
            return foodId;
        }

        public void setFoodId(Long foodId) {
            this.foodId = foodId;
        }

        public int getPortions() {
            return portions;
        }

        public void setPortions(int portions) {
            this.portions = portions;
        }
    }
}



