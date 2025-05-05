package com.example.ceybank.services;

import com.example.ceybank.models.Beverage;
import com.example.ceybank.models.BeverageBill;
import com.example.ceybank.models.BeverageBillItem;
import com.example.ceybank.models.ReservationRoom;
import com.example.ceybank.repositories.BeverageBillItemRepository;
import com.example.ceybank.repositories.BeverageBillRepository;
import com.example.ceybank.repositories.BeverageRepository;
import com.example.ceybank.repositories.ReservationRoomRepository;
import com.example.ceybank.responses.BeverageOrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BeverageOrderService {

    @Autowired
    private BeverageRepository beverageRepository;

    @Autowired
    private ReservationRoomRepository reservationRoomRepository;

    @Autowired
    private BeverageBillRepository beverageBillRepository;

    @Autowired
    private BeverageBillItemRepository beverageBillItemRepository;

    public void placeBeverageOrder(BeverageOrderRequest request) {
        ReservationRoom reservationRoom = reservationRoomRepository
                .findByReservationIdAndRoomNo(request.getReservationId(), request.getRoomNo())
                .orElseThrow(() -> new RuntimeException("Reservation room not found for given roomNo and reservationId"));

        BeverageBill beverageBill = new BeverageBill();
        beverageBill.setDate(request.getOrderDate());
        beverageBill.setMealType(request.getMealType());
        beverageBill.setReservationRoom(reservationRoom);
        beverageBill.setRoomNo(request.getRoomNo());

        List<BeverageBillItem> items = new ArrayList<>();
        double grandTotal = 0;

        for (BeverageOrderRequest.BeverageOrderItem itemReq : request.getItems()) {
            Beverage beverage = beverageRepository.findById(itemReq.getBeverageId())
                    .orElseThrow(() -> new RuntimeException("Beverage not found: " + itemReq.getBeverageId()));

            double total = beverage.getPrice() * itemReq.getBottlesOrGlasses();

            BeverageBillItem item = new BeverageBillItem();
            item.setBeverage(beverage);
            item.setBottlesOrGlasses(itemReq.getBottlesOrGlasses());
            item.setTotal(total);
            item.setBeverageBill(beverageBill);

            items.add(item);
            grandTotal += total;
        }

        beverageBill.setGrandTotal(grandTotal);
        beverageBill.setBeverageBillItems(items);

        beverageBillRepository.save(beverageBill);
        beverageBillItemRepository.saveAll(items);
    }
}
