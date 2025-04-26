package com.example.ceybank.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
public class Room {

    @Id
    @JsonProperty("roomNo")
    private String roomNo; // Room Number like 101, 102

    @ManyToOne
    @JoinColumn(name = "room_type_id")
    @JsonProperty("roomType")
    private RoomType roomType;

    @JsonProperty("status")
    private String status; // available, booked, maintenance, etc.

    // ----- Getters and Setters -----

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

