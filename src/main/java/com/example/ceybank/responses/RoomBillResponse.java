package com.example.ceybank.responses;

import java.time.LocalDate;
import java.util.List;

public class RoomBillResponse {
    private Long reservationId;
    private LocalDate inDate;
    private LocalDate outDate;
    private int nights;
    private double roomTotal;
    private List<RoomDetail> rooms;

    // Getters and Setters
    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
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

    public int getNights() {
        return nights;
    }

    public void setNights(int nights) {
        this.nights = nights;
    }

    public double getRoomTotal() {
        return roomTotal;
    }

    public void setRoomTotal(double roomTotal) {
        this.roomTotal = roomTotal;
    }

    public List<RoomDetail> getRooms() {
        return rooms;
    }

    public void setRooms(List<RoomDetail> rooms) {
        this.rooms = rooms;
    }

    // Nested static class
    public static class RoomDetail {
        private String roomNo;
        private String roomType;
        private double ratePerNight;

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

        public double getRatePerNight() {
            return ratePerNight;
        }

        public void setRatePerNight(double ratePerNight) {
            this.ratePerNight = ratePerNight;
        }
    }
}

