package com.example.ceybank.responses;


import java.util.List;

public class ApproveStoreRequisitionItemRequest {

    private Long itemId;
    private int approvedQuantity;

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public int getApprovedQuantity() {
        return approvedQuantity;
    }

    public void setApprovedQuantity(int approvedQuantity) {
        this.approvedQuantity = approvedQuantity;
    }


}


