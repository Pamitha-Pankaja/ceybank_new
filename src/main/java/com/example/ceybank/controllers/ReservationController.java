package com.example.ceybank.controllers;

import com.example.ceybank.models.*;
import com.example.ceybank.responses.*;
import com.example.ceybank.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

//    @PostMapping
//    public ResponseEntity<Reservation> createReservation(@RequestBody ReservationRequest request) {
//        Reservation reservation = reservationService.createReservation(request);
//        return ResponseEntity.ok(reservation);
//    }

    @PostMapping
    public ResponseEntity<ReservationResponse> createReservation(@RequestBody ReservationRequest request) {
        Reservation reservation = reservationService.createReservation(request);
        ReservationResponse response = reservationService.refactorResponse(reservation);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{reservationId}/bills")
    public ResponseEntity<ReservationBillResponse> getAllBillsByReservation(@PathVariable Long reservationId) {
        return ResponseEntity.ok(reservationService.getBillsForReservation(reservationId));
    }

    @GetMapping("/{reservationId}/final-bill")
    public ResponseEntity<ReservationFinalBillResponse> getFinalBill(@PathVariable Long reservationId) {
        return ResponseEntity.ok(reservationService.calculateFinalBill(reservationId));
    }


//    @GetMapping("/active")
//    public ResponseEntity<ActiveReservationResponse> getActiveReservation(
//            @RequestParam String roomNo,
//            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
//        return ResponseEntity.ok(reservationService.getActiveReservation(roomNo, date));
//    }

    @GetMapping("/active")
    public ResponseEntity<List<ActiveReservationResponse>> getActiveReservations(
            @RequestParam String roomNo,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(reservationService.getAllReservationsForRoomAndDate(roomNo, date));
    }



    @GetMapping("/full-details")
    public ResponseEntity<List<ReservationFullResponse>> getAllReservationDetails() {
        return ResponseEntity.ok(reservationService.getAllReservationDetails());
    }



    @GetMapping("/bills/meals")
    public ResponseEntity<List<FoodBillResponse>> getAllFoodBills() {
        return ResponseEntity.ok(reservationService.getAllFoodBillResponses());
    }

    @GetMapping("/bills/beverages")
    public ResponseEntity<List<BeverageBillResponse>> getAllBeverageBills() {
        return ResponseEntity.ok(reservationService.getAllBeverageBillResponses());
    }



    @GetMapping("/bills/meals/by-date")
    public ResponseEntity<List<FoodBillResponse>> getFoodBillsByDate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(reservationService.getFoodBillsByDate(date));
    }

    @GetMapping("/bills/beverages/by-date")
    public ResponseEntity<List<BeverageBillResponse>> getBeverageBillsByDate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(reservationService.getBeverageBillsByDate(date));
    }

    @GetMapping("/bills/meals/by-meal")
    public ResponseEntity<List<FoodBillResponse>> getFoodBillsByMealType(@RequestParam String mealType) {
        return ResponseEntity.ok(reservationService.getFoodBillsByMealType(mealType));
    }


    @GetMapping("/{reservationId}/room-bill")
    public ResponseEntity<RoomBillResponse> getRoomBill(@PathVariable Long reservationId) {
        return ResponseEntity.ok(reservationService.getRoomBillByReservation(reservationId));
    }


    @GetMapping("/room-bill")
    public ResponseEntity<List<RoomBillResponse>> getAllRoomBills() {
        return ResponseEntity.ok(reservationService.getAllRoomBills());
    }

    @GetMapping("/bills/meals/{foodBillId}")
    public ResponseEntity<FoodBillResponse> getFoodBillById(@PathVariable Long foodBillId) {
        return ResponseEntity.ok(reservationService.getFoodBillById(foodBillId));
    }


    @GetMapping("/bills/beverages/{beverageBillId}")
    public ResponseEntity<BeverageBillResponse> getBeverageBillById(@PathVariable Long beverageBillId) {
        return ResponseEntity.ok(reservationService.getBeverageBillById(beverageBillId));
    }

    @GetMapping("/by-nic")
    public ResponseEntity<CustomerResponse> getCustomerByNic(@RequestParam String nic) {
        return ResponseEntity.ok(reservationService.getCustomerByNic(nic));
    }



}

