package com.example.ceybank.controllers;

import com.example.ceybank.models.Reservation;
import com.example.ceybank.models.Room;
import com.example.ceybank.responses.ReservationRequest;
import com.example.ceybank.responses.ReservationResponse;
import com.example.ceybank.services.ReservationService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

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

