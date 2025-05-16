package com.example.ceybank.repositories;

import com.example.ceybank.models.FoodBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FoodBillRepository extends JpaRepository<FoodBill, Long> {

    List<FoodBill> findByReservationRoomId(Long reservationRoomId);

    List<FoodBill> findByDate(LocalDate date);
    List<FoodBill> findByMealType(String mealType);
}

