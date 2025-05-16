package com.example.ceybank.controllers;

import com.example.ceybank.models.Room;
import com.example.ceybank.responses.RoomAvailabilityResponse;
import com.example.ceybank.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping
    public ResponseEntity<Room> createRoom(@RequestBody Room room) {
        return ResponseEntity.ok(roomService.createRoom(room));
    }

    @GetMapping
    public ResponseEntity<List<Room>> getAllRooms() {
        return ResponseEntity.ok(roomService.getAllRooms());
    }

    @GetMapping("/{roomNo}")
    public ResponseEntity<Room> getRoomByRoomNo(@PathVariable String roomNo) {
        return ResponseEntity.ok(roomService.getRoomByRoomNo(roomNo));
    }

    @PutMapping("/{roomNo}")
    public ResponseEntity<Room> updateRoom(@PathVariable String roomNo, @RequestBody Room room) {
        return ResponseEntity.ok(roomService.updateRoom(roomNo, room));
    }

    @DeleteMapping("/{roomNo}")
    public ResponseEntity<Void> deleteRoom(@PathVariable String roomNo) {
        roomService.deleteRoom(roomNo);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/available")
    public ResponseEntity<List<Room>> getAvailableRooms() {
        return ResponseEntity.ok(roomService.getAvailableRooms());
    }


    @GetMapping("/available-on-date")
    public ResponseEntity<List<Room>> getAvailableRoomsOnDate(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(roomService.getAvailableRoomsByDate(date));
    }

    @GetMapping("/booked-on-date")
    public ResponseEntity<List<Room>> getBookedRoomsByDate(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(roomService.getBookedRoomsByDate(date));
    }

//    @GetMapping("/available-on-range")
//    public ResponseEntity<List<Room>> getAvailableRoomsOnRange(@RequestParam("inDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inDate,
//                                                               @RequestParam("outDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate outDate) {
//        return ResponseEntity.ok(roomService.getAvailableRoomsByDateRange(inDate, outDate));
//    }

    @GetMapping("/available-on-range")
    public ResponseEntity<List<RoomAvailabilityResponse>> getRoomAvailabilityByDateRange(
            @RequestParam("inDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inDate,
            @RequestParam("outDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate outDate) {
        return ResponseEntity.ok(roomService.getRoomAvailabilityPerDateRange(inDate, outDate));
    }


    @GetMapping("/booked-on-range")
    public ResponseEntity<List<Room>> getBookedRoomsOnRange(@RequestParam("inDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inDate,
                                                            @RequestParam("outDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate outDate) {
        return ResponseEntity.ok(roomService.getBookedRoomsByDateRange(inDate, outDate));
    }


    //with RoomType

    @GetMapping("/available-on-date-by-type")
    public ResponseEntity<List<Room>> getAvailableRoomsOnDateByType(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                                                    @RequestParam("roomTypeId") Long roomTypeId) {
        return ResponseEntity.ok(roomService.getAvailableRoomsByDateAndRoomType(date, roomTypeId));
    }

    @GetMapping("/available-on-range-by-type")
    public ResponseEntity<List<Room>> getAvailableRoomsOnRangeByType(@RequestParam("inDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inDate,
                                                                     @RequestParam("outDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate outDate,
                                                                     @RequestParam("roomTypeId") Long roomTypeId) {
        return ResponseEntity.ok(roomService.getAvailableRoomsByDateRangeAndRoomType(inDate, outDate, roomTypeId));
    }

}

