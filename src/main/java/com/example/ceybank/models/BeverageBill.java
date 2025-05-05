package com.example.ceybank.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class BeverageBill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long beverageBillId;

    private String roomNo;
    private LocalDate date;
    private double grandTotal;
    private String mealType; // Breakfast, Lunch, Dinner

    @OneToMany(mappedBy = "beverageBill", cascade = CascadeType.ALL)
    private List<BeverageBillItem> beverageBillItems;

    @ManyToOne
    @JoinColumn(name = "reservation_room_id")
    private ReservationRoom reservationRoom;

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

    public double getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(double grandTotal) {
        this.grandTotal = grandTotal;
    }

    public List<BeverageBillItem> getBeverageBillItems() {
        return beverageBillItems;
    }

    public void setBeverageBillItems(List<BeverageBillItem> beverageBillItems) {
        this.beverageBillItems = beverageBillItems;
    }

    public ReservationRoom getReservationRoom() {
        return reservationRoom;
    }

    public void setReservationRoom(ReservationRoom reservationRoom) {
        this.reservationRoom = reservationRoom;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }
}


