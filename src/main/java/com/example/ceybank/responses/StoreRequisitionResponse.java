package com.example.ceybank.responses;

import java.time.LocalDate;
import java.util.List;

public class StoreRequisitionResponse {
    private Long id;
    private String storeRequisitionId;
    private LocalDate date;
    private String status;
    private List<StoreRequisitionItemResponse> items;

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

    public void setStatus(String status) {
        this.status = status;
    }

    public List<StoreRequisitionItemResponse> getItems() {
        return items;
    }

    public void setItems(List<StoreRequisitionItemResponse> items) {
        this.items = items;
    }
}
