package com.example.ceybank.responses;

import java.time.LocalDate;
import java.util.List;

public class BeverageBillResponse {
    private Long beverageBillId;
    private String roomNo;
    private LocalDate date;
    private String mealType;
    private double grandTotal;
    private List<BeverageBillItemResponse> beverageBillItems;

    // Getters and Setters
    public Long getBeverageBillId() {
        return beverageBillId;
    }

    public void setBeverageBillId(Long beverageBillId) {
        this.beverageBillId = beverageBillId;
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

    public List<BeverageBillItemResponse> getBeverageBillItems() {
        return beverageBillItems;
    }

    public void setBeverageBillItems(List<BeverageBillItemResponse> beverageBillItems) {
        this.beverageBillItems = beverageBillItems;
    }

    // Nested static class
    public static class BeverageBillItemResponse {
        private Long id;
        private String beverageName;
        private int bottlesOrGlasses;
        private double total;

        // Getters and Setters
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getBeverageName() {
            return beverageName;
        }

        public void setBeverageName(String beverageName) {
            this.beverageName = beverageName;
        }

        public int getBottlesOrGlasses() {
            return bottlesOrGlasses;
        }

        public void setBottlesOrGlasses(int bottlesOrGlasses) {
            this.bottlesOrGlasses = bottlesOrGlasses;
        }

        public double getTotal() {
            return total;
        }

        public void setTotal(double total) {
            this.total = total;
        }
    }
}

