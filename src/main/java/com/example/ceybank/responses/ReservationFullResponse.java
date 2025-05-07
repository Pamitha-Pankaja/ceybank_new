package com.example.ceybank.responses;

import java.time.LocalDate;
import java.util.List;

public class ReservationFullResponse {

    private Long reservationId;
    private String customerName;
    private String nicPassportPf;
    private LocalDate inDate;
    private LocalDate outDate;
    private int days;
    private double total;
    private double advance;
    private int noOfGuests;
    private int adults;
    private int children;
    private String modeOfPayment;
    private String vehicleNos;
    private String billNos;
    private List<RoomInfo> rooms;

    // Getters and Setters
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

    public String getNicPassportPf() {
        return nicPassportPf;
    }

    public void setNicPassportPf(String nicPassportPf) {
        this.nicPassportPf = nicPassportPf;
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

    public String getVehicleNos() {
        return vehicleNos;
    }

    public void setVehicleNos(String vehicleNos) {
        this.vehicleNos = vehicleNos;
    }

    public String getBillNos() {
        return billNos;
    }

    public void setBillNos(String billNos) {
        this.billNos = billNos;
    }

    public List<RoomInfo> getRooms() {
        return rooms;
    }

    public void setRooms(List<RoomInfo> rooms) {
        this.rooms = rooms;
    }

    // Inner static class RoomInfo
    public static class RoomInfo {
        private String roomNo;
        private String roomType;
        private double currentRate;
        private List<BillSummary> foodBills;
        private List<BillSummary> beverageBills;

        // Getters and Setters
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

        public List<BillSummary> getFoodBills() {
            return foodBills;
        }

        public void setFoodBills(List<BillSummary> foodBills) {
            this.foodBills = foodBills;
        }

        public List<BillSummary> getBeverageBills() {
            return beverageBills;
        }

        public void setBeverageBills(List<BillSummary> beverageBills) {
            this.beverageBills = beverageBills;
        }
    }

    // Inner static class BillSummary
    public static class BillSummary {
        private Long billId;
        private String mealType;
        private LocalDate date;
        private double grandTotal;

        // Getters and Setters
        public Long getBillId() {
            return billId;
        }

        public void setBillId(Long billId) {
            this.billId = billId;
        }

        public String getMealType() {
            return mealType;
        }

        public void setMealType(String mealType) {
            this.mealType = mealType;
        }

        public LocalDate getDate() {
            return date;
        }

        public void setDate(LocalDate date) {
            this.date = date;
        }

        public double getGrandTotal() {
            return grandTotal;
        }

        public void setGrandTotal(double grandTotal) {
            this.grandTotal = grandTotal;
        }
    }
}

