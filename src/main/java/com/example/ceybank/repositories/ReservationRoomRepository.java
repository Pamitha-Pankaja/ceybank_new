package com.example.ceybank.repositories;

import com.example.ceybank.models.ReservationRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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


    @Query("SELECT rr FROM ReservationRoom rr " +
            "JOIN rr.reservation res " +
            "WHERE :date >= res.inDate AND :date < res.outDate")
    List<ReservationRoom> findRoomsBookedOnDate(@Param("date") LocalDate date);

    @Query("SELECT rr FROM ReservationRoom rr WHERE rr.reservation.reservationId = :reservationId AND rr.room.roomNo = :roomNo")
    Optional<ReservationRoom> findByReservationIdAndRoomNo(@Param("reservationId") Long reservationId, @Param("roomNo") String roomNo);


//    @Query("""
//    SELECT rr FROM ReservationRoom rr
//    JOIN rr.reservation r
//    WHERE rr.room.roomNo = :roomNo
//      AND :date >= r.inDate AND :date < r.outDate
//""")
//    Optional<ReservationRoom> findActiveReservationByRoomAndDate(
//            @Param("roomNo") String roomNo,
//            @Param("date") LocalDate date
//    );

    @Query("""
SELECT rr FROM ReservationRoom rr
JOIN rr.reservation r
WHERE rr.room.roomNo = :roomNo
  AND (:date >= r.inDate AND :date <= r.outDate)
""")
    List<ReservationRoom> findAllReservationsByRoomAndDate(
            @Param("roomNo") String roomNo,
            @Param("date") LocalDate date
    );




}
