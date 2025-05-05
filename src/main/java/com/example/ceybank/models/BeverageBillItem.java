package com.example.ceybank.models;

import jakarta.persistence.*;

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

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Beverage getBeverage() {
        return beverage;
    }

    public void setBeverage(Beverage beverage) {
        this.beverage = beverage;
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

    public BeverageBill getBeverageBill() {
        return beverageBill;
    }

    public void setBeverageBill(BeverageBill beverageBill) {
        this.beverageBill = beverageBill;
    }
}

