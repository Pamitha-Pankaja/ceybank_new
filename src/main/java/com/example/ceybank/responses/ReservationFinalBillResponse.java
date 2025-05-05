package com.example.ceybank.responses;

public class ReservationFinalBillResponse {
    private Long reservationId;
    private double roomCharges;
    private double foodTotal;
    private double beverageTotal;
    private double advance;
    private double finalTotal;

    // Constructor
    public ReservationFinalBillResponse(Long reservationId, double roomCharges, double foodTotal, double beverageTotal, double advance, double finalTotal) {
        this.reservationId = reservationId;
        this.roomCharges = roomCharges;
        this.foodTotal = foodTotal;
        this.beverageTotal = beverageTotal;
        this.advance = advance;
        this.finalTotal = finalTotal;
    }

    // Getters and Setters
    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public double getRoomCharges() {
        return roomCharges;
    }

    public void setRoomCharges(double roomCharges) {
        this.roomCharges = roomCharges;
    }

    public double getFoodTotal() {
        return foodTotal;
    }

    public void setFoodTotal(double foodTotal) {
        this.foodTotal = foodTotal;
    }

    public double getBeverageTotal() {
        return beverageTotal;
    }

    public void setBeverageTotal(double beverageTotal) {
        this.beverageTotal = beverageTotal;
    }

    public double getAdvance() {
        return advance;
    }

    public void setAdvance(double advance) {
        this.advance = advance;
    }

    public double getFinalTotal() {
        return finalTotal;
    }

    public void setFinalTotal(double finalTotal) {
        this.finalTotal = finalTotal;
    }
}

