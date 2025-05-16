package com.example.ceybank.repositories;

import com.example.ceybank.models.BeverageBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BeverageBillRepository extends JpaRepository<BeverageBill, Long> {

    List<BeverageBill> findByReservationRoomId(Long reservationRoomId);

    List<BeverageBill> findByDate(LocalDate date);

}
