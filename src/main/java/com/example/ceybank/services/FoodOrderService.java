package com.example.ceybank.services;

import com.example.ceybank.models.Food;
import com.example.ceybank.models.FoodBill;
import com.example.ceybank.models.FoodBillItem;
import com.example.ceybank.models.ReservationRoom;
import com.example.ceybank.repositories.FoodBillItemRepository;
import com.example.ceybank.repositories.FoodBillRepository;
import com.example.ceybank.repositories.FoodRepository;
import com.example.ceybank.repositories.ReservationRoomRepository;
import com.example.ceybank.responses.ActiveReservationResponse;
import com.example.ceybank.responses.FoodOrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class FoodOrderService {

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private ReservationRoomRepository reservationRoomRepository;

    @Autowired
    private FoodBillRepository foodBillRepository;

    @Autowired
    private FoodBillItemRepository foodBillItemRepository;

    public void placeFoodOrder(FoodOrderRequest request) {
        // Find ReservationRoom using reservationId and roomNo
        ReservationRoom reservationRoom = reservationRoomRepository
                .findByReservationIdAndRoomNo(request.getReservationId(), request.getRoomNo())
                .orElseThrow(() -> new RuntimeException("Reservation room not found for given roomNo and reservationId"));

        // Create new FoodBill
        FoodBill foodBill = new FoodBill();
        foodBill.setDate(request.getOrderDate());
        foodBill.setMealType(request.getMealType());
        foodBill.setReservationRoom(reservationRoom);
        foodBill.setRoomNo(request.getRoomNo());

        List<FoodBillItem> foodBillItems = new ArrayList<>();
        double grandTotal = 0;

        // Process each item in request
        for (FoodOrderRequest.FoodOrderItem orderItem : request.getItems()) {
            Food food = foodRepository.findById(orderItem.getFoodId())
                    .orElseThrow(() -> new RuntimeException("Food item not found: " + orderItem.getFoodId()));

            double total = food.getPrice() * orderItem.getPortions();

            FoodBillItem item = new FoodBillItem();
            item.setFood(food);
            item.setPortions(orderItem.getPortions());
            item.setTotal(total);
            item.setFoodBill(foodBill);

            foodBillItems.add(item);
            grandTotal += total;
        }

        foodBill.setGrandTotal(grandTotal);
        foodBill.setFoodBillItems(foodBillItems);

        foodBillRepository.save(foodBill);
        foodBillItemRepository.saveAll(foodBillItems);
    }





}

