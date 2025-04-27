package com.example.ceybank.repositories;

import com.example.ceybank.models.ReservationRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRoomRepository extends JpaRepository<ReservationRoom, Long> {

    // Find all reservation rooms by a specific room number
    List<ReservationRoom> findByRoomRoomNo(String roomNo);

    // Optional: Find all reservation rooms between date ranges (useful to find already booked rooms)
//    @Query("SELECT rr FROM ReservationRoom rr " +
//            "JOIN rr.reservation res " +
//            "WHERE (res.inDate <= :outDate) AND (res.outDate >= :inDate)")
//    List<ReservationRoom> findConflictingReservations(
//            @Param("inDate") LocalDate inDate,
//            @Param("outDate") LocalDate outDate);



    // Find reservation rooms that overlap with given dates
    @Query("SELECT rr FROM ReservationRoom rr " +
            "JOIN rr.reservation res " +
            "WHERE (res.inDate < :outDate) AND (res.outDate > :inDate)")
    List<ReservationRoom> findConflictingReservations(
            @Param("inDate") LocalDate inDate,
            @Param("outDate") LocalDate outDate
    );

}
