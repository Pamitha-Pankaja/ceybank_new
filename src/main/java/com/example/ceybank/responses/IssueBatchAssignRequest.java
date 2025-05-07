package com.example.ceybank.responses;

import java.time.LocalDate;
import java.util.List;

public class IssueBatchAssignRequest {
    private String issueNo;
    private LocalDate issuedDate;
    private List<Long> itemIds; // only IDs

    public String getIssueNo() {
        return issueNo;
    }

    public void setIssueNo(String issueNo) {
        this.issueNo = issueNo;
    }

    public LocalDate getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(LocalDate issuedDate) {
        this.issuedDate = issuedDate;
    }

    public List<Long> getItemIds() {
        return itemIds;
    }

    public void setItemIds(List<Long> itemIds) {
        this.itemIds = itemIds;
    }
}
