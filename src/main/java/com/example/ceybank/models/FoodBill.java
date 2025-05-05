package com.example.ceybank.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class FoodBill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long foodBillId;

    private String roomNo;
    private LocalDate date;
    private String mealType; // Breakfast, Lunch, Dinner

    private double grandTotal;

    @OneToMany(mappedBy = "foodBill", cascade = CascadeType.ALL)
    private List<FoodBillItem> foodBillItems;

    @ManyToOne
    @JoinColumn(name = "reservation_room_id")
    private ReservationRoom reservationRoom;

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

    public List<FoodBillItem> getFoodBillItems() {
        return foodBillItems;
    }

    public void setFoodBillItems(List<FoodBillItem> foodBillItems) {
        this.foodBillItems = foodBillItems;
    }

    public ReservationRoom getReservationRoom() {
        return reservationRoom;
    }

    public void setReservationRoom(ReservationRoom reservationRoom) {
        this.reservationRoom = reservationRoom;
    }
}

