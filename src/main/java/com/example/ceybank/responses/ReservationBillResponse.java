package com.example.ceybank.responses;

import java.time.LocalDate;
import java.util.List;

public class ReservationBillResponse {

    private Long reservationId;
    private List<RoomBills> roomBillsList;

    // Getters and Setters
    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public List<RoomBills> getRoomBillsList() {
        return roomBillsList;
    }

    public void setRoomBillsList(List<RoomBills> roomBillsList) {
        this.roomBillsList = roomBillsList;
    }

    // Inner static class - RoomBills
    public static class RoomBills {
        private String roomNo;
        private List<FoodBillSummary> foodBills;
        private List<BeverageBillSummary> beverageBills;

        // Getters and Setters
        public String getRoomNo() {
            return roomNo;
        }

        public void setRoomNo(String roomNo) {
            this.roomNo = roomNo;
        }

        public List<FoodBillSummary> getFoodBills() {
            return foodBills;
        }

        public void setFoodBills(List<FoodBillSummary> foodBills) {
            this.foodBills = foodBills;
        }

        public List<BeverageBillSummary> getBeverageBills() {
            return beverageBills;
        }

        public void setBeverageBills(List<BeverageBillSummary> beverageBills) {
            this.beverageBills = beverageBills;
        }
    }

    // Inner static class - FoodBillSummary
    public static class FoodBillSummary {
        private Long billId;
        private String mealType;
        private LocalDate date;
        private double grandTotal;

        // Getters and Setters
        public Long getBillId() {
            return billId;
        }

        public void setBillId(Long billId) {
            this.billId = billId;
        }

        public String getMealType() {
            return mealType;
        }

        public void setMealType(String mealType) {
            this.mealType = mealType;
        }

        public LocalDate getDate() {
            return date;
        }

        public void setDate(LocalDate date) {
            this.date = date;
        }

        public double getGrandTotal() {
            return grandTotal;
        }

        public void setGrandTotal(double grandTotal) {
            this.grandTotal = grandTotal;
        }
    }

    // Inner static class - BeverageBillSummary
    public static class BeverageBillSummary {
        private Long billId;
        private String mealType;
        private LocalDate date;
        private double grandTotal;

        // Getters and Setters
        public Long getBillId() {
            return billId;
        }

        public void setBillId(Long billId) {
            this.billId = billId;
        }

        public String getMealType() {
            return mealType;
        }

        public void setMealType(String mealType) {
            this.mealType = mealType;
        }

        public LocalDate getDate() {
            return date;
        }

        public void setDate(LocalDate date) {
            this.date = date;
        }

        public double getGrandTotal() {
            return grandTotal;
        }

        public void setGrandTotal(double grandTotal) {
            this.grandTotal = grandTotal;
        }
    }
}
