package com.example.ceybank.models;



import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "good_requisitions")
public class GoodRequisition {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;  // or goodRequisitionId
//
//    private String itemName;
//    private String itemCode;
//    private int requiredQuantity;
//    private int approvedQuantity;
//    private int issuedQuantity;  // this will trigger an 'OUT' transaction
//    private LocalDate issuedDate;
//
//    // Bi-directional link to transactions that reference this good requisition
//    @OneToMany(mappedBy = "goodRequisition", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Transaction> transactions;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;

    @JsonProperty("goodRequisitionId")
    private String goodRequisitionId; // e.g. SR001, SR002

    @JsonProperty("date")
    private LocalDate date;

    @JsonProperty("status")
    private String status;

    @OneToMany(mappedBy = "goodRequisition", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonProperty("items")
    private List<GoodRequisitionItem> items;

    // === Getters and Setters ===

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGoodRequisitionId() {
        return goodRequisitionId;
    }

    public void setGoodeRequisitionId(String goodRequisitionId) {
        this.goodRequisitionId = goodRequisitionId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<GoodRequisitionItem> getItems() {
        return items;
    }

    public void setItems(List<GoodRequisitionItem> items) {
        this.items = items;
    }


}
