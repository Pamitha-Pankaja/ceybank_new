package com.example.ceybank.responses;

import java.time.LocalDate;
import java.util.List;

import java.time.LocalDate;
import java.util.List;

public class RoomAvailabilityResponse {
    private String roomNo;
    private List<LocalDate> availableDates;

    // Getters and Setters
    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public List<LocalDate> getAvailableDates() {
        return availableDates;
    }

    public void setAvailableDates(List<LocalDate> availableDates) {
        this.availableDates = availableDates;
    }
}


