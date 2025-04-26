package com.example.ceybank.responses;

import java.time.LocalDate;
import java.util.List;

public class ReceiveGoodRequisitionBatchRequest {

    private String issueNo;
    private LocalDate receivedDate;
    private List<ReceiveGoodRequisitionItemRequest> items;

    // Getters and Setters
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
    public List<ReceiveGoodRequisitionItemRequest> getItems() {
        return items;
    }
}
