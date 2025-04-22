package com.example.ceybank.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "store_requisition_items")
public class StoreRequisitionItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemCode;
    private String itemName;
    private String unit;
    private int requiredQuantity;
    private int approvedQuantity;
    private int receivedQuantity;

    private double rate;
    private double total;
    private String grnNo;
    private LocalDate receivedDate;

    @ManyToOne
    @JoinColumn(name = "store_requisition_id")
    private StoreRequisition storeRequisition;

    @ManyToOne
    @JoinColumn(name = "inventory_item_id")
    private InventoryItem inventoryItem;

    @OneToOne(mappedBy = "storeRequisitionItem", cascade = CascadeType.ALL)
    private Transaction transaction;

    // Getters & Setters
}

