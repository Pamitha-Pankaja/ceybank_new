package com.example.ceybank.responses;

import java.util.List;

public class GrnItemUpdateRequest {
    private String grnNo; // optional use
    private List<ReceiveStoreRequisitionItemRequest> items;

    public String getGrnNo() {
        return grnNo;
    }

    public void setGrnNo(String grnNo) {
        this.grnNo = grnNo;
    }

    public List<ReceiveStoreRequisitionItemRequest> getItems() {
        return items;
    }

    public void setItems(List<ReceiveStoreRequisitionItemRequest> items) {
        this.items = items;
    }
}

