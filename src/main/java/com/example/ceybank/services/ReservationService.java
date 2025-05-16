package com.example.ceybank.services;

import com.example.ceybank.models.*;
import com.example.ceybank.repositories.*;
import com.example.ceybank.responses.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService {

    private final CustomerRepository customerRepository;
    private final RoomRepository roomRepository;
    private final ReservationRepository reservationRepository;
    private final ReservationRoomRepository reservationRoomRepository;

    @Autowired
    FoodBillRepository foodBillRepository;

    @Autowired
    BeverageBillRepository beverageBillRepository;

    public ReservationService(CustomerRepository customerRepository,
                              RoomRepository roomRepository,
                              ReservationRepository reservationRepository,
                              ReservationRoomRepository reservationRoomRepository) {
        this.customerRepository = customerRepository;
        this.roomRepository = roomRepository;
        this.reservationRepository = reservationRepository;
        this.reservationRoomRepository = reservationRoomRepository;
    }

//    @Transactional
//    public Reservation createReservation(ReservationRequest request) {
//
//        // Step 1: Save Customer
//        Customer customer = new Customer();
//        customer.setTitle(request.getTitle());
//        customer.setNameWithInitials(request.getNameWithInitials());
//        customer.setNameInFull(request.getNameInFull());
//        customer.setNationality(request.getNationality());
//        customer.setNicPassportPf(request.getNicPassportPf());
//        customer.setTelNo(request.getTelNo());
//        customer.setOfficeTel(request.getOfficeTel());
//        customer.setDurationOfStay(request.getDurationOfStay());
//        customer.setHomeAddress(request.getHomeAddress());
//        customer.setOfficeAddress(request.getOfficeAddress());
//        customer = customerRepository.save(customer);
//
//        // Step 2: Create Reservation
//        Reservation reservation = new Reservation();
//        reservation.setCustomer(customer);
//        reservation.setInDate(request.getInDate());
//        reservation.setOutDate(request.getOutDate());
//        reservation.setAdvance(request.getAdvance());
//        reservation.setNoOfGuests(request.getNoOfGuests());
//        reservation.setAdults(request.getAdults());
//        reservation.setChildren(request.getChildren());
//        reservation.setModeOfPayment(request.getModeOfPayment());
//        reservation.setVehicleNos(request.getVehicleNos());
//        reservation.setBillNos(request.getBillNos());
//
//        List<ReservationRoom> reservationRooms = new ArrayList<>();
//
//        double total = 0.0;
//
//        // Step 3: For each Room No → Check availability
//        for (String roomNo : request.getRoomNos()) {
//            Room room = roomRepository.findById(roomNo)
//                    .orElseThrow(() -> new RuntimeException("Room not found: " + roomNo));
//
//            // Check if room is available for the requested dates
//            boolean conflict = reservationRoomRepository.findConflictingReservations(request.getInDate(), request.getOutDate())
//                    .stream()
//                    .anyMatch(rr -> rr.getRoom().getRoomNo().equals(roomNo));
//
//            if (conflict) {
//                throw new RuntimeException("Room " + roomNo + " is already booked for the selected dates");
//            }
//
//            ReservationRoom reservationRoom = new ReservationRoom();
//            reservationRoom.setRoom(room);
//            reservationRoom.setReservation(reservation);
//
//            reservationRooms.add(reservationRoom);
//
//            // Add RoomType Rate to Total
//            total += room.getRoomType().getCurrentRate();
//        }
//
//        reservation.setTotal(total);
//        reservation.setReservationRooms(reservationRooms);
//
//        return reservationRepository.save(reservation);
//    }


    @Transactional
    public Reservation createReservation(ReservationRequest request) {
        // Step 1: Save Customer
        Customer customer = new Customer();
        customer.setTitle(request.getTitle());
        customer.setNameWithInitials(request.getNameWithInitials());
        customer.setNameInFull(request.getNameInFull());
        customer.setNationality(request.getNationality());
        customer.setNicPassportPf(request.getNicPassportPf());
        customer.setTelNo(request.getTelNo());
        customer.setOfficeTel(request.getOfficeTel());
        customer.setDurationOfStay(request.getDurationOfStay());
        customer.setHomeAddress(request.getHomeAddress());
        customer.setOfficeAddress(request.getOfficeAddress());
        customer = customerRepository.save(customer);

        // Step 2: Create Reservation
        Reservation reservation = new Reservation();
        reservation.setCustomer(customer);
        reservation.setInDate(request.getInDate());
        reservation.setOutDate(request.getOutDate());
        reservation.setAdvance(request.getAdvance());
        reservation.setNoOfGuests(request.getNoOfGuests());
        reservation.setAdults(request.getAdults());
        reservation.setChildren(request.getChildren());
        reservation.setModeOfPayment(request.getModeOfPayment());
        reservation.setVehicleNos(request.getVehicleNos());
        reservation.setBillNos(request.getBillNos());

        long nights = ChronoUnit.DAYS.between(request.getInDate(), request.getOutDate());
        reservation.setDays((int) nights); // Store it in reservation too

        List<ReservationRoom> reservationRooms = new ArrayList<>();
        double total = 0.0;

        // Step 3: For each Room No → Check availability
        for (String roomNo : request.getRoomNos()) {
            Room room = roomRepository.findById(roomNo)
                    .orElseThrow(() -> new RuntimeException("Room not found: " + roomNo));

            boolean conflict = reservationRoomRepository.findConflictingReservations(request.getInDate(), request.getOutDate())
                    .stream()
                    .anyMatch(rr -> rr.getRoom().getRoomNo().equals(roomNo));

            if (conflict) {
                throw new RuntimeException("Room " + roomNo + " is already booked for the selected dates");
            }

            ReservationRoom reservationRoom = new ReservationRoom();
            reservationRoom.setRoom(room);
            reservationRoom.setReservation(reservation);

            reservationRooms.add(reservationRoom);

            // Multiply room rate by nights
            total += room.getRoomType().getCurrentRate() * nights;
        }

        reservation.setTotal(total);
        reservation.setReservationRooms(reservationRooms);

        return reservationRepository.save(reservation);
    }




    public ReservationBillResponse getBillsForReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        ReservationBillResponse response = new ReservationBillResponse();
        response.setReservationId(reservation.getReservationId());

        List<ReservationBillResponse.RoomBills> roomBillsList = new ArrayList<>();

        for (ReservationRoom rr : reservation.getReservationRooms()) {
            ReservationBillResponse.RoomBills roomBills = new ReservationBillResponse.RoomBills();
            roomBills.setRoomNo(rr.getRoom().getRoomNo());

            List<ReservationBillResponse.FoodBillSummary> foodSummaries = rr.getFoodBills().stream().map(fb -> {
                ReservationBillResponse.FoodBillSummary summary = new ReservationBillResponse.FoodBillSummary();
                summary.setBillId(fb.getFoodBillId());
                summary.setMealType(fb.getMealType());
                summary.setDate(fb.getDate());
                summary.setGrandTotal(fb.getGrandTotal());
                return summary;
            }).toList();

            List<ReservationBillResponse.BeverageBillSummary> beverageSummaries = rr.getBeverageBills().stream().map(bb -> {
                ReservationBillResponse.BeverageBillSummary summary = new ReservationBillResponse.BeverageBillSummary();
                summary.setBillId(bb.getBeverageBillId());
                summary.setMealType(bb.getMealType());
                summary.setDate(bb.getDate());
                summary.setGrandTotal(bb.getGrandTotal());
                return summary;
            }).toList();

            roomBills.setFoodBills(foodSummaries);
            roomBills.setBeverageBills(beverageSummaries);

            roomBillsList.add(roomBills);
        }

        response.setRoomBillsList(roomBillsList);
        return response;
    }




    public ReservationFinalBillResponse calculateFinalBill(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        double foodTotal = 0.0;
        double beverageTotal = 0.0;

        for (ReservationRoom rr : reservation.getReservationRooms()) {
            if (rr.getFoodBills() != null) {
                foodTotal += rr.getFoodBills().stream().mapToDouble(FoodBill::getGrandTotal).sum();
            }
            if (rr.getBeverageBills() != null) {
                beverageTotal += rr.getBeverageBills().stream().mapToDouble(BeverageBill::getGrandTotal).sum();
            }
        }

        double roomCharges = reservation.getTotal(); // already stored
        double advance = reservation.getAdvance();

        double finalTotal = roomCharges + foodTotal + beverageTotal - advance;

        // Optionally persist these values
        reservation.setFoodTotal(foodTotal);
        reservation.setBeverageTotal(beverageTotal);
        reservation.setFinalTotal(finalTotal);
        reservationRepository.save(reservation);

        return new ReservationFinalBillResponse(
                reservation.getReservationId(),
                roomCharges,
                foodTotal,
                beverageTotal,
                advance,
                finalTotal
        );
    }






    public ReservationResponse refactorResponse(Reservation reservation) {
        ReservationResponse response = new ReservationResponse();

        response.setReservationId(reservation.getReservationId());
        response.setCustomerName(reservation.getCustomer().getNameWithInitials());
        response.setInDate(reservation.getInDate());
        response.setOutDate(reservation.getOutDate());
        response.setDays(reservation.getDays());
        response.setTotal(reservation.getTotal());
        response.setAdvance(reservation.getAdvance());
        response.setNoOfGuests(reservation.getNoOfGuests());
        response.setAdults(reservation.getAdults());
        response.setChildren(reservation.getChildren());
        response.setModeOfPayment(reservation.getModeOfPayment());

        List<ReservationResponse.ReservedRoomDetails> roomsList = new ArrayList<>();
        for (ReservationRoom rr : reservation.getReservationRooms()) {
            ReservationResponse.ReservedRoomDetails rd = new ReservationResponse.ReservedRoomDetails();
            rd.setRoomNo(rr.getRoom().getRoomNo());
            rd.setRoomType(rr.getRoom().getRoomType().getName());
            rd.setCurrentRate(rr.getRoom().getRoomType().getCurrentRate());
            roomsList.add(rd);
        }

        response.setRooms(roomsList);

        return response;
    }


    public ActiveReservationResponse getActiveReservation(String roomNo, LocalDate date) {
        ReservationRoom rr = reservationRoomRepository.findActiveReservationByRoomAndDate(roomNo, date)
                .orElseThrow(() -> new RuntimeException("No active reservation found for this room and date"));

        ActiveReservationResponse response = new ActiveReservationResponse();
        response.setReservationId(rr.getReservation().getReservationId());
        response.setName(rr.getReservation().getCustomer().getNameInFull());
        response.setNicPassportPf(rr.getReservation().getCustomer().getNicPassportPf());
        response.setRoomNo(roomNo);

        return response;
    }



    public List<ReservationFullResponse> getAllReservationDetails() {
        List<Reservation> reservations = reservationRepository.findAll();
        List<ReservationFullResponse> responses = new ArrayList<>();

        for (Reservation reservation : reservations) {
            ReservationFullResponse r = new ReservationFullResponse();
            r.setReservationId(reservation.getReservationId());
            r.setCustomerName(reservation.getCustomer().getNameInFull());
            r.setNicPassportPf(reservation.getCustomer().getNicPassportPf());
            r.setInDate(reservation.getInDate());
            r.setOutDate(reservation.getOutDate());
            r.setDays(reservation.getDays());
            r.setTotal(reservation.getTotal());
            r.setAdvance(reservation.getAdvance());
            r.setNoOfGuests(reservation.getNoOfGuests());
            r.setAdults(reservation.getAdults());
            r.setChildren(reservation.getChildren());
            r.setModeOfPayment(reservation.getModeOfPayment());
            r.setVehicleNos(reservation.getVehicleNos());
            r.setBillNos(reservation.getBillNos());

            List<ReservationFullResponse.RoomInfo> roomInfos = new ArrayList<>();
            for (ReservationRoom rr : reservation.getReservationRooms()) {
                ReservationFullResponse.RoomInfo roomInfo = new ReservationFullResponse.RoomInfo();
                roomInfo.setRoomNo(rr.getRoom().getRoomNo());
                roomInfo.setRoomType(rr.getRoom().getRoomType().getName());
                roomInfo.setCurrentRate(rr.getRoom().getRoomType().getCurrentRate());

                // Food Bills
                List<ReservationFullResponse.BillSummary> foodBillSummaries = rr.getFoodBills().stream().map(fb -> {
                    ReservationFullResponse.BillSummary s = new ReservationFullResponse.BillSummary();
                    s.setBillId(fb.getFoodBillId());
                    s.setMealType(fb.getMealType());
                    s.setDate(fb.getDate());
                    s.setGrandTotal(fb.getGrandTotal());
                    return s;
                }).toList();

                // Beverage Bills
                List<ReservationFullResponse.BillSummary> beverageBillSummaries = rr.getBeverageBills().stream().map(bb -> {
                    ReservationFullResponse.BillSummary s = new ReservationFullResponse.BillSummary();
                    s.setBillId(bb.getBeverageBillId());
                    s.setMealType(bb.getMealType());
                    s.setDate(bb.getDate());
                    s.setGrandTotal(bb.getGrandTotal());
                    return s;
                }).toList();

                roomInfo.setFoodBills(foodBillSummaries);
                roomInfo.setBeverageBills(beverageBillSummaries);

                roomInfos.add(roomInfo);
            }

            r.setRooms(roomInfos);
            responses.add(r);
        }

        return responses;
    }




    public List<FoodBillResponse> getAllFoodBillResponses() {
        return foodBillRepository.findAll().stream().map(fb -> {
            FoodBillResponse res = new FoodBillResponse();
            res.setFoodBillId(fb.getFoodBillId());
            res.setRoomNo(fb.getReservationRoom().getRoom().getRoomNo());
            res.setDate(fb.getDate());
            res.setMealType(fb.getMealType());
            res.setGrandTotal(fb.getGrandTotal());

            List<FoodBillResponse.FoodBillItemResponse> items = fb.getFoodBillItems().stream().map(item -> {
                FoodBillResponse.FoodBillItemResponse i = new FoodBillResponse.FoodBillItemResponse();
                i.setId(item.getId());
                i.setFoodName(item.getFood().getName());
                i.setPortions(item.getPortions());
                i.setTotal(item.getTotal());
                return i;
            }).toList();

            res.setFoodBillItems(items);
            return res;
        }).toList();
    }


    public List<BeverageBillResponse> getAllBeverageBillResponses() {
        return beverageBillRepository.findAll().stream().map(bb -> {
            BeverageBillResponse res = new BeverageBillResponse();
            res.setBeverageBillId(bb.getBeverageBillId());
            res.setRoomNo(bb.getReservationRoom().getRoom().getRoomNo());
            res.setDate(bb.getDate());
            res.setMealType(bb.getMealType());
            res.setGrandTotal(bb.getGrandTotal());

            List<BeverageBillResponse.BeverageBillItemResponse> items = bb.getBeverageBillItems().stream().map(item -> {
                BeverageBillResponse.BeverageBillItemResponse i = new BeverageBillResponse.BeverageBillItemResponse();
                i.setId(item.getId());
                i.setBeverageName(item.getBeverage().getName());
                i.setBottlesOrGlasses(item.getBottlesOrGlasses());
                i.setTotal(item.getTotal());
                return i;
            }).toList();

            res.setBeverageBillItems(items);
            return res;
        }).toList();
    }



    public List<FoodBillResponse> getFoodBillsByDate(LocalDate date) {
        return foodBillRepository.findByDate(date).stream().map(fb -> {
            FoodBillResponse res = new FoodBillResponse();
            res.setFoodBillId(fb.getFoodBillId());
            res.setRoomNo(fb.getReservationRoom().getRoom().getRoomNo());
            res.setDate(fb.getDate());
            res.setMealType(fb.getMealType());
            res.setGrandTotal(fb.getGrandTotal());

            List<FoodBillResponse.FoodBillItemResponse> items = fb.getFoodBillItems().stream().map(item -> {
                FoodBillResponse.FoodBillItemResponse i = new FoodBillResponse.FoodBillItemResponse();
                i.setId(item.getId());
                i.setFoodName(item.getFood().getName());
                i.setPortions(item.getPortions());
                i.setTotal(item.getTotal());
                return i;
            }).toList();

            res.setFoodBillItems(items);
            return res;
        }).toList();
    }



    public List<BeverageBillResponse> getBeverageBillsByDate(LocalDate date) {
        return beverageBillRepository.findByDate(date).stream().map(bb -> {
            BeverageBillResponse res = new BeverageBillResponse();
            res.setBeverageBillId(bb.getBeverageBillId());
            res.setRoomNo(bb.getReservationRoom().getRoom().getRoomNo());
            res.setDate(bb.getDate());
            res.setMealType(bb.getMealType());
            res.setGrandTotal(bb.getGrandTotal());

            List<BeverageBillResponse.BeverageBillItemResponse> items = bb.getBeverageBillItems().stream().map(item -> {
                BeverageBillResponse.BeverageBillItemResponse i = new BeverageBillResponse.BeverageBillItemResponse();
                i.setId(item.getId());
                i.setBeverageName(item.getBeverage().getName());
                i.setBottlesOrGlasses(item.getBottlesOrGlasses());
                i.setTotal(item.getTotal());
                return i;
            }).toList();

            res.setBeverageBillItems(items);
            return res;
        }).toList();
    }


    public List<FoodBillResponse> getFoodBillsByMealType(String mealType) {
        return foodBillRepository.findByMealType(mealType).stream().map(fb -> {
            FoodBillResponse res = new FoodBillResponse();
            res.setFoodBillId(fb.getFoodBillId());
            res.setRoomNo(fb.getReservationRoom().getRoom().getRoomNo());
            res.setDate(fb.getDate());
            res.setMealType(fb.getMealType());
            res.setGrandTotal(fb.getGrandTotal());

            List<FoodBillResponse.FoodBillItemResponse> items = fb.getFoodBillItems().stream().map(item -> {
                FoodBillResponse.FoodBillItemResponse i = new FoodBillResponse.FoodBillItemResponse();
                i.setId(item.getId());
                i.setFoodName(item.getFood().getName());
                i.setPortions(item.getPortions());
                i.setTotal(item.getTotal());
                return i;
            }).toList();

            res.setFoodBillItems(items);
            return res;
        }).toList();
    }





}

