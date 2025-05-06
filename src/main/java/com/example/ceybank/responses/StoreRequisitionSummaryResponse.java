package com.example.ceybank.responses;

import java.time.LocalDate;

public class StoreRequisitionSummaryResponse {
    private Long id;
    private String storeRequisitionId;
    private LocalDate date;
    private String status;

    // Getters and Setters
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
}
