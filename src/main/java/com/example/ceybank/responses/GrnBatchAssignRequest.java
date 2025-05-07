package com.example.ceybank.responses;

import java.time.LocalDate;
import java.util.List;

import java.time.LocalDate;
import java.util.List;

public class GrnBatchAssignRequest {
    private String grnNo;
    private LocalDate receivedDate;
    private List<Long> itemIds; // ✅ make sure the name matches

    public String getGrnNo() {
        return grnNo;
    }

    public void setGrnNo(String grnNo) {
        this.grnNo = grnNo;
    }

    public LocalDate getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(LocalDate receivedDate) {
        this.receivedDate = receivedDate;
    }

    public List<Long> getItemIds() {
        return itemIds;
    }

    public void setItemIds(List<Long> itemIds) {
        this.itemIds = itemIds;
    }
}


