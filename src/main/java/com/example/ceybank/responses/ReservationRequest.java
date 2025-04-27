package com.example.ceybank.responses;

import java.time.LocalDate;
import java.util.List;

public class ReservationRequest {

    private String title;
    private String nameWithInitials;
    private String nameInFull;
    private String nationality;
    private String nicPassportPf;
    private String telNo;
    private String officeTel;
    private String durationOfStay;
    private String homeAddress;
    private String officeAddress;

    private LocalDate inDate;
    private LocalDate outDate;
    private double advance;
    private int noOfGuests;
    private int adults;
    private int children;
    private String modeOfPayment;
    private String vehicleNos;
    private String billNos;

    private List<String> roomNos;

    // Getters and Setters
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

    public List<String> getRoomNos() {
        return roomNos;
    }

    public void setRoomNos(List<String> roomNos) {
        this.roomNos = roomNos;
    }
}

