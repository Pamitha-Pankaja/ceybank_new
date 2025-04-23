package com.example.ceybank.responses;

import java.time.LocalDate;
import java.util.List;

public class StoreRequisitionResponse {
    private Long id;
    private String storeRequisitionId;
    private LocalDate date;
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

    public List<StoreRequisitionItemResponse> getItems() {
        return items;
    }

    public void setItems(List<StoreRequisitionItemResponse> items) {
        this.items = items;
    }
}


