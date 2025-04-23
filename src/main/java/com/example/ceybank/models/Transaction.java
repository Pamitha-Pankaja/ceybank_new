package com.example.ceybank.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("tid")
    private Long tid;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    @JsonProperty("inventoryItem")
    private InventoryItem inventoryItem;

    @OneToOne
    @JoinColumn(name = "store_requisition_item_id")
    private StoreRequisitionItem storeRequisitionItem;

    @ManyToOne
    @JoinColumn(name = "good_requisition_id")
    @JsonProperty("goodRequisition")
    private GoodRequisition goodRequisition;

    @JsonProperty("date")
    private LocalDate date;

    @JsonProperty("receivedQuantity")
    private int receivedQuantity;

    @JsonProperty("issuedQuantity")
    private int issuedQuantity;

    // === Getters and Setters ===

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }

    public InventoryItem getInventoryItem() {
        return inventoryItem;
    }

    public void setInventoryItem(InventoryItem inventoryItem) {
        this.inventoryItem = inventoryItem;
    }

    public StoreRequisitionItem getStoreRequisitionItem() {
        return storeRequisitionItem;
    }

    public void setStoreRequisitionItem(StoreRequisitionItem storeRequisitionItem) {
        this.storeRequisitionItem = storeRequisitionItem;
    }

    public GoodRequisition getGoodRequisition() {
        return goodRequisition;
    }

    public void setGoodRequisition(GoodRequisition goodRequisition) {
        this.goodRequisition = goodRequisition;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getReceivedQuantity() {
        return receivedQuantity;
    }

    public void setReceivedQuantity(int receivedQuantity) {
        this.receivedQuantity = receivedQuantity;
    }

    public int getIssuedQuantity() {
        return issuedQuantity;
    }

    public void setIssuedQuantity(int issuedQuantity) {
        this.issuedQuantity = issuedQuantity;
    }
}

