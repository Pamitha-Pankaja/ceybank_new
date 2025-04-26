package com.example.ceybank.responses;

import java.time.LocalDate;
import java.util.List;

public class GoodRequisitionResponse {
    private Long id;
    private String goodRequisitionId;
    private LocalDate date;
    private List<GoodRequisitionItemResponse> items;

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

    public List<GoodRequisitionItemResponse> getItems() {
        return items;
    }

    public void setItems(List<GoodRequisitionItemResponse> items) {
        this.items = items;
    }
}
