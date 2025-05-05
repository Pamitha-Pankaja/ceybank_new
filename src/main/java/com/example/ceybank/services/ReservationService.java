package com.example.ceybank.services;

import com.example.ceybank.models.*;
import com.example.ceybank.repositories.CustomerRepository;
import com.example.ceybank.repositories.ReservationRepository;
import com.example.ceybank.repositories.ReservationRoomRepository;
import com.example.ceybank.repositories.RoomRepository;
import com.example.ceybank.responses.ReservationBillResponse;
import com.example.ceybank.responses.ReservationFinalBillResponse;
import com.example.ceybank.responses.ReservationRequest;
import com.example.ceybank.responses.ReservationResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService {

    private final CustomerRepository customerRepository;
    private final RoomRepository roomRepository;
    private final ReservationRepository reservationRepository;
    private final ReservationRoomRepository reservationRoomRepository;

    public ReservationService(CustomerRepository customerRepository,
                              RoomRepository roomRepository,
                              ReservationRepository reservationRepository,
                              ReservationRoomRepository reservationRoomRepository) {
        this.customerRepository = customerRepository;
        this.roomRepository = roomRepository;
        this.reservationRepository = reservationRepository;
        this.reservationRoomRepository = reservationRoomRepository;
    }

//    @Transactional
//    public Reservation createReservation(ReservationRequest request) {
//
//        // Step 1: Save Customer
//        Customer customer = new Customer();
//        customer.setTitle(request.getTitle());
//        customer.setNameWithInitials(request.getNameWithInitials());
//        customer.setNameInFull(request.getNameInFull());
//        customer.setNationality(request.getNationality());
//        customer.setNicPassportPf(request.getNicPassportPf());
//        customer.setTelNo(request.getTelNo());
//        customer.setOfficeTel(request.getOfficeTel());
//        customer.setDurationOfStay(request.getDurationOfStay());
//        customer.setHomeAddress(request.getHomeAddress());
//        customer.setOfficeAddress(request.getOfficeAddress());
//        customer = customerRepository.save(customer);
//
//        // Step 2: Create Reservation
//        Reservation reservation = new Reservation();
//        reservation.setCustomer(customer);
//        reservation.setInDate(request.getInDate());
//        reservation.setOutDate(request.getOutDate());
//        reservation.setAdvance(request.getAdvance());
//        reservation.setNoOfGuests(request.getNoOfGuests());
//        reservation.setAdults(request.getAdults());
//        reservation.setChildren(request.getChildren());
//        reservation.setModeOfPayment(request.getModeOfPayment());
//        reservation.setVehicleNos(request.getVehicleNos());
//        reservation.setBillNos(request.getBillNos());
//
//        List<ReservationRoom> reservationRooms = new ArrayList<>();
//
//        double total = 0.0;
//
//        // Step 3: For each Room No → Check availability
//        for (String roomNo : request.getRoomNos()) {
//            Room room = roomRepository.findById(roomNo)
//                    .orElseThrow(() -> new RuntimeException("Room not found: " + roomNo));
//
//            // Check if room is available for the requested dates
//            boolean conflict = reservationRoomRepository.findConflictingReservations(request.getInDate(), request.getOutDate())
//                    .stream()
//                    .anyMatch(rr -> rr.getRoom().getRoomNo().equals(roomNo));
//
//            if (conflict) {
//                throw new RuntimeException("Room " + roomNo + " is already booked for the selected dates");
//            }
//
//            ReservationRoom reservationRoom = new ReservationRoom();
//            reservationRoom.setRoom(room);
//            reservationRoom.setReservation(reservation);
//
//            reservationRooms.add(reservationRoom);
//
//            // Add RoomType Rate to Total
//            total += room.getRoomType().getCurrentRate();
//        }
//
//        reservation.setTotal(total);
//        reservation.setReservationRooms(reservationRooms);
//
//        return reservationRepository.save(reservation);
//    }


    @Transactional
    public Reservation createReservation(ReservationRequest request) {
        // Step 1: Save Customer
        Customer customer = new Customer();
        customer.setTitle(request.getTitle());
        customer.setNameWithInitials(request.getNameWithInitials());
        customer.setNameInFull(request.getNameInFull());
        customer.setNationality(request.getNationality());
        customer.setNicPassportPf(request.getNicPassportPf());
        customer.setTelNo(request.getTelNo());
        customer.setOfficeTel(request.getOfficeTel());
        customer.setDurationOfStay(request.getDurationOfStay());
        customer.setHomeAddress(request.getHomeAddress());
        customer.setOfficeAddress(request.getOfficeAddress());
        customer = customerRepository.save(customer);

        // Step 2: Create Reservation
        Reservation reservation = new Reservation();
        reservation.setCustomer(customer);
        reservation.setInDate(request.getInDate());
        reservation.setOutDate(request.getOutDate());
        reservation.setAdvance(request.getAdvance());
        reservation.setNoOfGuests(request.getNoOfGuests());
        reservation.setAdults(request.getAdults());
        reservation.setChildren(request.getChildren());
        reservation.setModeOfPayment(request.getModeOfPayment());
        reservation.setVehicleNos(request.getVehicleNos());
        reservation.setBillNos(request.getBillNos());

        long nights = ChronoUnit.DAYS.between(request.getInDate(), request.getOutDate());
        reservation.setDays((int) nights); // Store it in reservation too

        List<ReservationRoom> reservationRooms = new ArrayList<>();
        double total = 0.0;

        // Step 3: For each Room No → Check availability
        for (String roomNo : request.getRoomNos()) {
            Room room = roomRepository.findById(roomNo)
                    .orElseThrow(() -> new RuntimeException("Room not found: " + roomNo));

            boolean conflict = reservationRoomRepository.findConflictingReservations(request.getInDate(), request.getOutDate())
                    .stream()
                    .anyMatch(rr -> rr.getRoom().getRoomNo().equals(roomNo));

            if (conflict) {
                throw new RuntimeException("Room " + roomNo + " is already booked for the selected dates");
            }

            ReservationRoom reservationRoom = new ReservationRoom();
            reservationRoom.setRoom(room);
            reservationRoom.setReservation(reservation);

            reservationRooms.add(reservationRoom);

            // Multiply room rate by nights
            total += room.getRoomType().getCurrentRate() * nights;
        }

        reservation.setTotal(total);
        reservation.setReservationRooms(reservationRooms);

        return reservationRepository.save(reservation);
    }




    public ReservationBillResponse getBillsForReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        ReservationBillResponse response = new ReservationBillResponse();
        response.setReservationId(reservation.getReservationId());

        List<ReservationBillResponse.RoomBills> roomBillsList = new ArrayList<>();

        for (ReservationRoom rr : reservation.getReservationRooms()) {
            ReservationBillResponse.RoomBills roomBills = new ReservationBillResponse.RoomBills();
            roomBills.setRoomNo(rr.getRoom().getRoomNo());

            List<ReservationBillResponse.FoodBillSummary> foodSummaries = rr.getFoodBills().stream().map(fb -> {
                ReservationBillResponse.FoodBillSummary summary = new ReservationBillResponse.FoodBillSummary();
                summary.setBillId(fb.getFoodBillId());
                summary.setMealType(fb.getMealType());
                summary.setDate(fb.getDate());
                summary.setGrandTotal(fb.getGrandTotal());
                return summary;
            }).toList();

            List<ReservationBillResponse.BeverageBillSummary> beverageSummaries = rr.getBeverageBills().stream().map(bb -> {
                ReservationBillResponse.BeverageBillSummary summary = new ReservationBillResponse.BeverageBillSummary();
                summary.setBillId(bb.getBeverageBillId());
                summary.setMealType(bb.getMealType());
                summary.setDate(bb.getDate());
                summary.setGrandTotal(bb.getGrandTotal());
                return summary;
            }).toList();

            roomBills.setFoodBills(foodSummaries);
            roomBills.setBeverageBills(beverageSummaries);

            roomBillsList.add(roomBills);
        }

        response.setRoomBillsList(roomBillsList);
        return response;
    }




    public ReservationFinalBillResponse calculateFinalBill(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        double foodTotal = 0.0;
        double beverageTotal = 0.0;

        for (ReservationRoom rr : reservation.getReservationRooms()) {
            if (rr.getFoodBills() != null) {
                foodTotal += rr.getFoodBills().stream().mapToDouble(FoodBill::getGrandTotal).sum();
            }
            if (rr.getBeverageBills() != null) {
                beverageTotal += rr.getBeverageBills().stream().mapToDouble(BeverageBill::getGrandTotal).sum();
            }
        }

        double roomCharges = reservation.getTotal(); // already stored
        double advance = reservation.getAdvance();

        double finalTotal = roomCharges + foodTotal + beverageTotal - advance;

        // Optionally persist these values
        reservation.setFoodTotal(foodTotal);
        reservation.setBeverageTotal(beverageTotal);
        reservation.setFinalTotal(finalTotal);
        reservationRepository.save(reservation);

        return new ReservationFinalBillResponse(
                reservation.getReservationId(),
                roomCharges,
                foodTotal,
                beverageTotal,
                advance,
                finalTotal
        );
    }






    public ReservationResponse refactorResponse(Reservation reservation) {
        ReservationResponse response = new ReservationResponse();

        response.setReservationId(reservation.getReservationId());
        response.setCustomerName(reservation.getCustomer().getNameWithInitials());
        response.setInDate(reservation.getInDate());
        response.setOutDate(reservation.getOutDate());
        response.setDays(reservation.getDays());
        response.setTotal(reservation.getTotal());
        response.setAdvance(reservation.getAdvance());
        response.setNoOfGuests(reservation.getNoOfGuests());
        response.setAdults(reservation.getAdults());
        response.setChildren(reservation.getChildren());
        response.setModeOfPayment(reservation.getModeOfPayment());

        List<ReservationResponse.ReservedRoomDetails> roomsList = new ArrayList<>();
        for (ReservationRoom rr : reservation.getReservationRooms()) {
            ReservationResponse.ReservedRoomDetails rd = new ReservationResponse.ReservedRoomDetails();
            rd.setRoomNo(rr.getRoom().getRoomNo());
            rd.setRoomType(rr.getRoom().getRoomType().getName());
            rd.setCurrentRate(rr.getRoom().getRoomType().getCurrentRate());
            roomsList.add(rd);
        }

        response.setRooms(roomsList);

        return response;
    }
}

