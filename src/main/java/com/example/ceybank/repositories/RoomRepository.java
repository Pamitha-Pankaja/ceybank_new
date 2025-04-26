package com.example.ceybank.repositories;

import com.example.ceybank.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, String> {

    // Find all available rooms
    List<Room> findByStatus(String status);
}
