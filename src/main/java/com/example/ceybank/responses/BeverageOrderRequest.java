package com.example.ceybank.responses;

import java.time.LocalDate;
import java.util.List;

public class BeverageOrderRequest {

    private String roomNo;
    private Long reservationId;
    private LocalDate orderDate;
    private String mealType;
    private List<BeverageOrderItem> items;

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

    public List<BeverageOrderItem> getItems() {
        return items;
    }

    public void setItems(List<BeverageOrderItem> items) {
        this.items = items;
    }

    // Inner static class
    public static class BeverageOrderItem {
        private Long beverageId;
        private int bottlesOrGlasses;

        // Getters and Setters
        public Long getBeverageId() {
            return beverageId;
        }

        public void setBeverageId(Long beverageId) {
            this.beverageId = beverageId;
        }

        public int getBottlesOrGlasses() {
            return bottlesOrGlasses;
        }

        public void setBottlesOrGlasses(int bottlesOrGlasses) {
            this.bottlesOrGlasses = bottlesOrGlasses;
        }
    }
}

