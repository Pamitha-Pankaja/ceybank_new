package com.example.ceybank.services;

import com.example.ceybank.models.Room;
import com.example.ceybank.repositories.RoomRepository;
import com.example.ceybank.repositories.RoomTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final RoomTypeRepository roomTypeRepository;

    public RoomService(RoomRepository roomRepository, RoomTypeRepository roomTypeRepository) {
        this.roomRepository = roomRepository;
        this.roomTypeRepository = roomTypeRepository;
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
}


