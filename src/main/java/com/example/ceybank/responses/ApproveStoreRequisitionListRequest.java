package com.example.ceybank.responses;

import java.util.List;

public class ApproveStoreRequisitionListRequest {
    private List<Long> requisitionIds;

    public List<Long> getRequisitionIds() {
        return requisitionIds;
    }

    public void setRequisitionIds(List<Long> requisitionIds) {
        this.requisitionIds = requisitionIds;
    }
}

