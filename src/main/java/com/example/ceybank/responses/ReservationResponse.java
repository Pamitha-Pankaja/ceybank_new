package com.example.ceybank.responses;

import java.time.LocalDate;
import java.util.List;

public class ReservationResponse {

    private Long reservationId;
    private String customerName;
    private LocalDate inDate;
    private LocalDate outDate;
    private int days;
    private double total;
    private double advance;
    private int noOfGuests;
    private int adults;
    private int children;
    private String modeOfPayment;
    private List<ReservedRoomDetails> rooms;

    // Inner class for reserved room details
    public static class ReservedRoomDetails {
        private String roomNo;
        private String roomType;
        private double currentRate;

        public String getRoomNo() {
            return roomNo;
        }

        public void setRoomNo(String roomNo) {
            this.roomNo = roomNo;
        }

        public String getRoomType() {
            return roomType;
        }

        public void setRoomType(String roomType) {
            this.roomType = roomType;
        }

        public double getCurrentRate() {
            return currentRate;
        }

        public void setCurrentRate(double currentRate) {
            this.currentRate = currentRate;
        }
    }

    // Getters and Setters for ReservationResponse fields

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public LocalDate getInDate() {
        return inDate;
    }

    public void setInDate(LocalDate inDate) {
        this.inDate = inDate;
    }

    public LocalDate getOutDate() {
        return outDate;
    }

    public void setOutDate(LocalDate outDate) {
        this.outDate = outDate;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getAdvance() {
        return advance;
    }

    public void setAdvance(double advance) {
        this.advance = advance;
    }

    public int getNoOfGuests() {
        return noOfGuests;
    }

    public void setNoOfGuests(int noOfGuests) {
        this.noOfGuests = noOfGuests;
    }

    public int getAdults() {
        return adults;
    }

    public void setAdults(int adults) {
        this.adults = adults;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    public String getModeOfPayment() {
        return modeOfPayment;
    }

    public void setModeOfPayment(String modeOfPayment) {
        this.modeOfPayment = modeOfPayment;
    }

    public List<ReservedRoomDetails> getRooms() {
        return rooms;
    }

    public void setRooms(List<ReservedRoomDetails> rooms) {
        this.rooms = rooms;
    }
}


