package com.example.ceybank.models;

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
}

