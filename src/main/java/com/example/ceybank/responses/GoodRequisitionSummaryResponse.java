package com.example.ceybank.responses;

import java.time.LocalDate;

public class GoodRequisitionSummaryResponse {
    private Long id;
    private String goodRequisitionId;
    private LocalDate date;
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGoodRequisitionId() {
        return goodRequisitionId;
    }

    public void setGoodRequisitionId(String goodRequisitionId) {
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


}
