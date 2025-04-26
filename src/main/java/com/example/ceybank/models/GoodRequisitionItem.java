package com.example.ceybank.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "good_requisition_items")
public class GoodRequisitionItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;

    @JsonProperty("itemCode")
    private String itemCode;

    @JsonProperty("itemName")
    private String itemName;

    @JsonProperty("unit")
    private String unit;

    @JsonProperty("requiredQuantity")
    private int requiredQuantity;

    @JsonProperty("approvedQuantity")
    private int approvedQuantity;

    @JsonProperty("issuedQuantity")
    private int issuedQuantity;

    @JsonProperty("issueNo")
    private String issueNo;

    @JsonProperty("receivedDate")
    private LocalDate receivedDate;

    @ManyToOne
    @JoinColumn(name = "good_requisition_id")
    private GoodRequisition goodRequisition;

    @ManyToOne
    @JoinColumn(name = "inventory_item_id")
    private InventoryItem inventoryItem;

    @OneToOne(mappedBy = "goodRequisitionItem", cascade = CascadeType.ALL)
    private Transaction transaction;

    // === Getters and Setters ===

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getRequiredQuantity() {
        return requiredQuantity;
    }

    public void setRequiredQuantity(int requiredQuantity) {
        this.requiredQuantity = requiredQuantity;
    }

    public int getApprovedQuantity() {
        return approvedQuantity;
    }

    public void setApprovedQuantity(int approvedQuantity) {
        this.approvedQuantity = approvedQuantity;
    }

    public int getIssuedQuantity() {
        return issuedQuantity;
    }

    public void setIssuedQuantity(int issuedQuantity) {
        this.issuedQuantity = issuedQuantity;
    }

    public String getIssueNo() {
        return issueNo;
    }

    public void setIssueNo(String issueNo) {
        this.issueNo = issueNo;
    }


    public LocalDate getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(LocalDate receivedDate) {
        this.receivedDate = receivedDate;
    }

    public GoodRequisition getGoodRequisition() {
        return goodRequisition;
    }

    public void setGoodRequisition(GoodRequisition goodRequisition) {
        this.goodRequisition = goodRequisition;
    }

    public InventoryItem getInventoryItem() {
        return inventoryItem;
    }

    public void setInventoryItem(InventoryItem inventoryItem) {
        this.inventoryItem = inventoryItem;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
