package com.example.ceybank.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class ReservationRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "room_no")
    @JsonProperty("room")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "reservation_id")
    @JsonProperty("reservation")
    private Reservation reservation;

    @OneToMany(mappedBy = "reservationRoom", cascade = CascadeType.ALL)
    private List<FoodBill> foodBills;

    @OneToMany(mappedBy = "reservationRoom", cascade = CascadeType.ALL)
    private List<BeverageBill> beverageBills;


    // ----- Getters and Setters -----

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}

