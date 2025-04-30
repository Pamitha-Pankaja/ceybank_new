package com.example.ceybank.services;

import com.example.ceybank.models.Customer;
import com.example.ceybank.models.Reservation;
import com.example.ceybank.models.ReservationRoom;
import com.example.ceybank.models.Room;
import com.example.ceybank.repositories.CustomerRepository;
import com.example.ceybank.repositories.ReservationRepository;
import com.example.ceybank.repositories.ReservationRoomRepository;
import com.example.ceybank.repositories.RoomRepository;
import com.example.ceybank.responses.ReservationRequest;
import com.example.ceybank.responses.ReservationResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

        List<ReservationRoom> reservationRooms = new ArrayList<>();

        double total = 0.0;

        // Step 3: For each Room No → Check availability
        for (String roomNo : request.getRoomNos()) {
            Room room = roomRepository.findById(roomNo)
                    .orElseThrow(() -> new RuntimeException("Room not found: " + roomNo));

            // Check if room is available for the requested dates
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

            // Add RoomType Rate to Total
            total += room.getRoomType().getCurrentRate();
        }

        reservation.setTotal(total);
        reservation.setReservationRooms(reservationRooms);

        return reservationRepository.save(reservation);
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

