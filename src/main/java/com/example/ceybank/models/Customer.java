package com.example.ceybank.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("customerId")
    private Long customerId;

    @JsonProperty("title")
    private String title;

    @JsonProperty("nameWithInitials")
    private String nameWithInitials;

    @JsonProperty("nameInFull")
    private String nameInFull;

    @JsonProperty("nationality")
    private String nationality;

    @JsonProperty("nicPassportPf")
    private String nicPassportPf;

    @JsonProperty("telNo")
    private String telNo;

    @JsonProperty("officeTel")
    private String officeTel;

    @JsonProperty("durationOfStay")
    private String durationOfStay;

    @JsonProperty("homeAddress")
    private String homeAddress;

    @JsonProperty("officeAddress")
    private String officeAddress;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonProperty("reservations")
    private List<Reservation> reservations;

    // ----- Getters and Setters -----

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNameWithInitials() {
        return nameWithInitials;
    }

    public void setNameWithInitials(String nameWithInitials) {
        this.nameWithInitials = nameWithInitials;
    }

    public String getNameInFull() {
        return nameInFull;
    }

    public void setNameInFull(String nameInFull) {
        this.nameInFull = nameInFull;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getNicPassportPf() {
        return nicPassportPf;
    }

    public void setNicPassportPf(String nicPassportPf) {
        this.nicPassportPf = nicPassportPf;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public String getOfficeTel() {
        return officeTel;
    }

    public void setOfficeTel(String officeTel) {
        this.officeTel = officeTel;
    }

    public String getDurationOfStay() {
        return durationOfStay;
    }

    public void setDurationOfStay(String durationOfStay) {
        this.durationOfStay = durationOfStay;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getOfficeAddress() {
        return officeAddress;
    }

    public void setOfficeAddress(String officeAddress) {
        this.officeAddress = officeAddress;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}

