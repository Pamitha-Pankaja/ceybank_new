package com.example.ceybank.responses;

public class ActiveReservationResponse {
    private Long reservationId;
    private String name;
    private String nicPassportPf;
    private String roomNo;

    // Getters and Setters
    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNicPassportPf() {
        return nicPassportPf;
    }

    public void setNicPassportPf(String nicPassportPf) {
        this.nicPassportPf = nicPassportPf;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }
}

