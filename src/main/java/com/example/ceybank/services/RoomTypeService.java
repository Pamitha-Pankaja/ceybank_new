package com.example.ceybank.services;

import com.example.ceybank.models.RoomType;
import com.example.ceybank.repositories.RoomTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RoomTypeService {

    private final RoomTypeRepository roomTypeRepository;

    public RoomTypeService(RoomTypeRepository roomTypeRepository) {
        this.roomTypeRepository = roomTypeRepository;
    }

    public RoomType createRoomType(RoomType roomType) {
        return roomTypeRepository.save(roomType);
    }

    public List<RoomType> getAllRoomTypes() {
        return roomTypeRepository.findAll();
    }

    public RoomType getRoomTypeById(Long id) {
        return roomTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("RoomType not found with id " + id));
    }

    public RoomType updateRoomType(Long id, RoomType updatedRoomType) {
        RoomType existing = getRoomTypeById(id);
        existing.setName(updatedRoomType.getName());
        existing.setCurrentRate(updatedRoomType.getCurrentRate());
        return roomTypeRepository.save(existing);
    }

    public void deleteRoomType(Long id) {
        roomTypeRepository.deleteById(id);
    }
}


