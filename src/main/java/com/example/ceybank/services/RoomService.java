package com.example.ceybank.services;

import com.example.ceybank.models.ReservationRoom;
import com.example.ceybank.models.Room;
import com.example.ceybank.repositories.ReservationRoomRepository;
import com.example.ceybank.repositories.RoomRepository;
import com.example.ceybank.repositories.RoomTypeRepository;
import com.example.ceybank.responses.RoomAvailabilityResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final RoomTypeRepository roomTypeRepository;
    private final ReservationRoomRepository reservationRoomRepository;

    public RoomService(RoomRepository roomRepository, RoomTypeRepository roomTypeRepository, ReservationRoomRepository reservationRoomRepository) {
        this.roomRepository = roomRepository;
        this.roomTypeRepository = roomTypeRepository;
        this.reservationRoomRepository = reservationRoomRepository;
    }

    public Room createRoom(Room room) {
        return roomRepository.save(room);
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Room getRoomByRoomNo(String roomNo) {
        return roomRepository.findById(roomNo)
                .orElseThrow(() -> new RuntimeException("Room not found with roomNo " + roomNo));
    }

    public Room updateRoom(String roomNo, Room updatedRoom) {
        Room existing = getRoomByRoomNo(roomNo);
        existing.setStatus(updatedRoom.getStatus());
        existing.setRoomType(updatedRoom.getRoomType());
        return roomRepository.save(existing);
    }

    public void deleteRoom(String roomNo) {
        roomRepository.deleteById(roomNo);
    }

    public List<Room> getAvailableRooms() {
        return roomRepository.findByStatus("available");
    }


    public List<Room> getAvailableRoomsByDate(LocalDate date) {
        List<Room> allAvailableRooms = roomRepository.findByStatus("available");

        List<String> bookedRoomNos = reservationRoomRepository.findRoomsBookedOnDate(date)
                .stream()
                .map(rr -> rr.getRoom().getRoomNo())
                .distinct()
                .toList();

        return allAvailableRooms.stream()
                .filter(room -> !bookedRoomNos.contains(room.getRoomNo()))
                .toList();
    }

    public List<Room> getBookedRoomsByDate(LocalDate date) {
        return reservationRoomRepository.findRoomsBookedOnDate(date)
                .stream()
                .map(ReservationRoom::getRoom)
                .distinct()
                .toList();
    }

//    public List<Room> getAvailableRoomsByDateRange(LocalDate inDate, LocalDate outDate) {
//        List<Room> allAvailableRooms = roomRepository.findByStatus("available");
//
//        List<String> bookedRoomNos = reservationRoomRepository.findConflictingReservations(inDate, outDate)
//                .stream()
//                .map(rr -> rr.getRoom().getRoomNo())
//                .distinct()
//                .toList();
//
//        return allAvailableRooms.stream()
//                .filter(room -> !bookedRoomNos.contains(room.getRoomNo()))
//                .toList();
//    }
//


    public List<RoomAvailabilityResponse> getRoomAvailabilityPerDateRange(LocalDate inDate, LocalDate outDate) {
        List<Room> availableRooms = roomRepository.findByStatus("available");
        Map<String, List<LocalDate>> availabilityMap = new HashMap<>();

        // Loop through each date
        for (LocalDate date = inDate; date.isBefore(outDate); date = date.plusDays(1)) {

            // Get all rooms booked for this date
            List<String> bookedRoomNos = reservationRoomRepository.findRoomsBookedOnDate(date)
                    .stream()
                    .map(rr -> rr.getRoom().getRoomNo())
                    .toList();

            // For each available room, check if it's not booked on this date
            for (Room room : availableRooms) {
                if (!bookedRoomNos.contains(room.getRoomNo())) {
                    availabilityMap
                            .computeIfAbsent(room.getRoomNo(), k -> new ArrayList<>())
                            .add(date);
                }
            }
        }

        // Map result to DTOs
        return availabilityMap.entrySet().stream().map(entry -> {
            RoomAvailabilityResponse dto = new RoomAvailabilityResponse();
            dto.setRoomNo(entry.getKey());
            dto.setAvailableDates(entry.getValue());
            return dto;
        }).toList();
    }


    public List<Room> getBookedRoomsByDateRange(LocalDate inDate, LocalDate outDate) {
        return reservationRoomRepository.findConflictingReservations(inDate, outDate)
                .stream()
                .map(ReservationRoom::getRoom)
                .distinct()
                .toList();
    }



    //with RoomType

    public List<Room> getAvailableRoomsByDateAndRoomType(LocalDate date, Long roomTypeId) {
        List<Room> allAvailableRooms = roomRepository.findByStatus("available")
                .stream()
                .filter(room -> room.getRoomType().getRoomTypeId().equals(roomTypeId))
                .toList();

        List<String> bookedRoomNos = reservationRoomRepository.findRoomsBookedOnDate(date)
                .stream()
                .map(rr -> rr.getRoom().getRoomNo())
                .distinct()
                .toList();

        return allAvailableRooms.stream()
                .filter(room -> !bookedRoomNos.contains(room.getRoomNo()))
                .toList();
    }

    public List<Room> getAvailableRoomsByDateRangeAndRoomType(LocalDate inDate, LocalDate outDate, Long roomTypeId) {
        List<Room> allAvailableRooms = roomRepository.findByStatus("available")
                .stream()
                .filter(room -> room.getRoomType().getRoomTypeId().equals(roomTypeId))
                .toList();

        List<String> bookedRoomNos = reservationRoomRepository.findConflictingReservations(inDate, outDate)
                .stream()
                .map(rr -> rr.getRoom().getRoomNo())
                .distinct()
                .toList();

        return allAvailableRooms.stream()
                .filter(room -> !bookedRoomNos.contains(room.getRoomNo()))
                .toList();
    }

}


