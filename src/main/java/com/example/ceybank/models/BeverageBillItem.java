package com.example.ceybank.models;

import jakarta.persistence.*;

@Entity
public class BeverageBillItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "beverage_id")
    private Beverage beverage;

    private int bottlesOrGlasses;
    private double total; // item.price * bottles

    @ManyToOne
    @JoinColumn(name = "beverage_bill_id")
    private BeverageBill beverageBill;
}
