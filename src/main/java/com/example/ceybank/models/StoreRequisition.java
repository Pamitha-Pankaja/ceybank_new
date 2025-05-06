package com.example.ceybank.models;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "store_requisitions")
public class StoreRequisition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;

    @JsonProperty("storeRequisitionId")
    private String storeRequisitionId; // e.g. SR001, SR002

    @JsonProperty("date")
    private LocalDate date;

    @JsonProperty("status")
    private String status;

    @OneToMany(mappedBy = "storeRequisition", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonProperty("items")
    private List<StoreRequisitionItem> items;

    // === Getters and Setters ===

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStoreRequisitionId() {
        return storeRequisitionId;
    }

    public void setStoreRequisitionId(String storeRequisitionId) {
        this.storeRequisitionId = storeRequisitionId;
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

    public void setStatus(java.lang.String status) {
        this.status = status;
    }

    public List<StoreRequisitionItem> getItems() {
        return items;
    }

    public void setItems(List<StoreRequisitionItem> items) {
        this.items = items;
    }
}


