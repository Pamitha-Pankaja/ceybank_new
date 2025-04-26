package com.example.ceybank.responses;

public class ReceiveGoodRequisitionItemRequest {
    private Long itemId;
    private int issuedQuantity;

//    private String grnNo;
//    private LocalDate receivedDate;

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public int getIssuedQuantity() {
        return issuedQuantity;
    }

    public void setIssuedQuantity(int issuedQuantity) {
        this.issuedQuantity = issuedQuantity;
    }

}
