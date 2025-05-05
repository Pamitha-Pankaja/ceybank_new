package com.example.ceybank.models;

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

}
