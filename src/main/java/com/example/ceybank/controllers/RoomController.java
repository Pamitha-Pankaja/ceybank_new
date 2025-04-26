package com.example.ceybank.controllers;

import com.example.ceybank.models.Room;
import com.example.ceybank.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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
}

