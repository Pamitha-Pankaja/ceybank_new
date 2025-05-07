package com.example.ceybank.responses;

import java.util.List;

public class IssueItemUpdateRequest {
    private String issueNo;
    private List<ReceiveGoodRequisitionItemRequest> items;

    // Getters and Setters
    public String getIssueNo() {
        return issueNo;
    }

    public void setIssueNo(String issueNo) {
        this.issueNo = issueNo;
    }

    public List<ReceiveGoodRequisitionItemRequest> getItems() {
        return items;
    }

    public void setItems(List<ReceiveGoodRequisitionItemRequest> items) {
        this.items = items;
    }
}
