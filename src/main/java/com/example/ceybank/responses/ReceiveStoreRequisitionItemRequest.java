package com.example.ceybank.responses;


import java.time.LocalDate;

public class ReceiveStoreRequisitionItemRequest {
    private Long itemId;
    private int receivedQuantity;
    private double rate;
    private double total;
//    private String grnNo;
//    private LocalDate receivedDate;

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public int getReceivedQuantity() {
        return receivedQuantity;
    }

    public void setReceivedQuantity(int receivedQuantity) {
        this.receivedQuantity = receivedQuantity;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

//    public String getGrnNo() {
//        return grnNo;
//    }
//
//    public void setGrnNo(String grnNo) {
//        this.grnNo = grnNo;
//    }
//
//    public LocalDate getReceivedDate() {
//        return receivedDate;
//    }
//
//    public void setReceivedDate(LocalDate receivedDate) {
//        this.receivedDate = receivedDate;
//    }
}
