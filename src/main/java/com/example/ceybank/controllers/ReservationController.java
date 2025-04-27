package com.example.ceybank.controllers;

import com.example.ceybank.models.Reservation;
import com.example.ceybank.responses.ReservationRequest;
import com.example.ceybank.responses.ReservationResponse;
import com.example.ceybank.services.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

