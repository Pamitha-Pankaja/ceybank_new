package com.example.ceybank.responses;

import java.time.LocalDate;
import java.util.List;

public class ReceiveStoreRequisitionBatchRequest {
    private String grnNo;
    private LocalDate receivedDate;
    private List<ReceiveStoreRequisitionItemRequest> items;

    // Getters and Setters
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
    public List<ReceiveStoreRequisitionItemRequest> getItems() {
        return items;
    }
}
