package com.example.ceybank.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class BeverageBill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long beverageBillId;

    private String roomNo;
    private LocalDate date;

    private double grandTotal;

    @OneToMany(mappedBy = "beverageBill", cascade = CascadeType.ALL)
    private List<BeverageBillItem> beverageBillItems;

    @ManyToOne
    @JoinColumn(name = "reservation_room_id")
    private ReservationRoom reservationRoom;

}

