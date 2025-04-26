package com.example.ceybank.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("reservationId")
    private Long reservationId;

    @JsonProperty("inDate")
    private LocalDate inDate;

    @JsonProperty("outDate")
    private LocalDate outDate;

    @JsonProperty("days")
    private int days;

    @JsonProperty("total")
    private double total;

    @JsonProperty("advance")
    private double advance;

    @JsonProperty("noOfGuests")
    private int noOfGuests;

    @JsonProperty("adults")
    private int adults;

    @JsonProperty("children")
    private int children;

    @JsonProperty("modeOfPayment")
    private String modeOfPayment;

    @JsonProperty("vehicleNos")
    private String vehicleNos;

    @JsonProperty("billNos")
    private String billNos;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonProperty("customer")
    private Customer customer;

    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL)
    @JsonProperty("reservationRooms")
    private List<ReservationRoom> reservationRooms;

    // ----- Getters and Setters -----

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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<ReservationRoom> getReservationRooms() {
        return reservationRooms;
    }

    public void setReservationRooms(List<ReservationRoom> reservationRooms) {
        this.reservationRooms = reservationRooms;
    }
}

