package com.example.ceybank.services;

import com.example.ceybank.repositories.StoreRequisitionItemRepository;
import com.example.ceybank.responses.*;
import org.springframework.stereotype.Service;
import com.example.ceybank.models.*;
import com.example.ceybank.repositories.InventoryItemRepository;
import com.example.ceybank.repositories.StoreRequisitionRepository;
import com.example.ceybank.repositories.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

@Service
public class StoreRequisitionService {

    @Autowired
    private StoreRequisitionRepository storeRequisitionRepository;

    @Autowired
    private InventoryItemRepository inventoryItemRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private StoreRequisitionItemRepository storeRequisitionItemRepository;


//    @Transactional
//    public StoreRequisition createStoreRequisition(StoreRequisition requisition) {
//
//        // Set parent in each child item first
//        for (StoreRequisitionItem item : requisition.getItems()) {
//            InventoryItem inventoryItem = inventoryItemRepository
//                    .findByItemCode(item.getItemCode())
//                    .orElseThrow(() -> new RuntimeException("Item code not found: " + item.getItemCode()));
//
//            item.setStoreRequisition(requisition);
//            item.setInventoryItem(inventoryItem);
//        }
//
//        // Save StoreRequisition and its items (thanks to CascadeType.ALL)
//        StoreRequisition savedRequisition = storeRequisitionRepository.save(requisition);
//
//        // Now loop again for each item and handle Transaction creation
//        for (StoreRequisitionItem item : savedRequisition.getItems()) {
//            if (item.getReceivedQuantity() > 0) {
//                Transaction transaction = new Transaction();
//                transaction.setInventoryItem(item.getInventoryItem());
//                transaction.setStoreRequisitionItem(item);
//                transaction.setReceivedQuantity(item.getReceivedQuantity());
//                transaction.setDate(LocalDate.now());
//
//                // Save transaction
//                transactionRepository.save(transaction);
//
//                // Update inventory quantity
//                InventoryItem inventoryItem = item.getInventoryItem();
//                inventoryItem.setQuantity(inventoryItem.getQuantity() + item.getReceivedQuantity());
//                inventoryItemRepository.save(inventoryItem);
//
//                // Link back to item
//                item.setTransaction(transaction);
//            }
//        }
//
//        return savedRequisition;
//    }


    @Transactional
    public StoreRequisition createStoreRequisition(StoreRequisition requisition) {
        requisition.setStatus("NOT_APPROVED"); // ✅ Default value
        for (StoreRequisitionItem item : requisition.getItems()) {
            InventoryItem inventoryItem = inventoryItemRepository
                    .findByItemCode(item.getItemCode())
                    .orElseThrow(() -> new RuntimeException("Item code not found: " + item.getItemCode()));

            item.setStoreRequisition(requisition);
            item.setInventoryItem(inventoryItem);
        }

        return storeRequisitionRepository.save(requisition); // only basic data saved here
    }

    public void approveItem(ApproveStoreRequisitionItemRequest request) {
        StoreRequisitionItem item = storeRequisitionItemRepository.findById(request.getItemId())
                .orElseThrow(() -> new RuntimeException("Store Requisition Item not found"));
        item.setApprovedQuantity(request.getApprovedQuantity());
        storeRequisitionItemRepository.save(item);
    }

//    @Transactional
//    public void receiveItem(ReceiveStoreRequisitionItemRequest request) {
//        StoreRequisitionItem item = storeRequisitionItemRepository.findById(request.getItemId())
//                .orElseThrow(() -> new RuntimeException("Store Requisition Item not found"));
//
//        item.setReceivedQuantity(request.getReceivedQuantity());
//        item.setRate(request.getRate());
//        item.setTotal(request.getTotal());
//        item.setGrnNo(request.getGrnNo());
//        item.setReceivedDate(request.getReceivedDate());
//
//        // Create transaction
//        Transaction transaction = new Transaction();
//        transaction.setInventoryItem(item.getInventoryItem());
//        transaction.setStoreRequisitionItem(item);
//        transaction.setReceivedQuantity(request.getReceivedQuantity());
//        transaction.setDate(LocalDate.now());
//
//        transactionRepository.save(transaction);
//        item.setTransaction(transaction);
//
//        // Update inventory
//        InventoryItem inventoryItem = item.getInventoryItem();
//        inventoryItem.setQuantity(inventoryItem.getQuantity() + request.getReceivedQuantity());
//        inventoryItemRepository.save(inventoryItem);
//
//        storeRequisitionItemRepository.save(item);
//    }


    @Transactional
    public void receiveBatch(ReceiveStoreRequisitionBatchRequest request) {
        for (ReceiveStoreRequisitionItemRequest itemRequest : request.getItems()) {
            StoreRequisitionItem item = storeRequisitionItemRepository.findById(itemRequest.getItemId())
                    .orElseThrow(() -> new RuntimeException("Item not found"));

            item.setReceivedQuantity(itemRequest.getReceivedQuantity());
            item.setRate(itemRequest.getRate());
            item.setTotal(itemRequest.getTotal());
            item.setGrnNo(request.getGrnNo()); // Set common GRN number
            item.setReceivedDate(request.getReceivedDate());

            InventoryItem inventoryItem = item.getInventoryItem();

// Calculate new balance before saving transaction
            int newBalance = inventoryItem.getQuantity() + itemRequest.getReceivedQuantity();

// Create transaction with balance
            Transaction transaction = new Transaction();
            transaction.setInventoryItem(inventoryItem);
            transaction.setStoreRequisitionItem(item);
            transaction.setReceivedQuantity(itemRequest.getReceivedQuantity());
            transaction.setDate(LocalDate.now());
            transaction.setBalance(newBalance); // ✅ NEW LINE


            transactionRepository.save(transaction);
            item.setTransaction(transaction);

            // Update inventory quantity
           // InventoryItem inventoryItem = item.getInventoryItem();
            //inventoryItem.setQuantity(inventoryItem.getQuantity() + itemRequest.getReceivedQuantity());
            inventoryItem.setQuantity(newBalance);
            inventoryItemRepository.save(inventoryItem);

            storeRequisitionItemRepository.save(item);
        }
    }


    public void approveRequisitionStatus(Long id) {
        StoreRequisition requisition = storeRequisitionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Store Requisition not found"));

        requisition.setStatus("APPROVED");
        storeRequisitionRepository.save(requisition);
    }


    public List<StoreRequisitionResponse> getAllRequisitionResponses() {
        List<StoreRequisition> requisitions = storeRequisitionRepository.findAll();

        return requisitions.stream()
                .map(this::refactorResponse)
                .toList();
    }

    public List<StoreRequisitionItemResponse> getRequisitionItems(Long requisitionId) {
        StoreRequisition requisition = storeRequisitionRepository.findById(requisitionId)
                .orElseThrow(() -> new RuntimeException("Store Requisition not found"));

        return requisition.getItems().stream().map(item -> {
            StoreRequisitionItemResponse itemResponse = new StoreRequisitionItemResponse();
            itemResponse.setId(item.getId());
            itemResponse.setItemCode(item.getItemCode());
            itemResponse.setItemName(item.getItemName());
            itemResponse.setUnit(item.getUnit());
            itemResponse.setRequiredQuantity(item.getRequiredQuantity());
            itemResponse.setApprovedQuantity(item.getApprovedQuantity());
            itemResponse.setReceivedQuantity(item.getReceivedQuantity());
            itemResponse.setRate(item.getRate());
            itemResponse.setTotal(item.getTotal());
            itemResponse.setGrnNo(item.getGrnNo());
            itemResponse.setReceivedDate(item.getReceivedDate());
            itemResponse.setTransactionId(item.getTransaction() != null ? item.getTransaction().getTid() : null);
            return itemResponse;
        }).toList();
    }






    public StoreRequisitionResponse refactorResponse(StoreRequisition requisition) {
        StoreRequisitionResponse response = new StoreRequisitionResponse();
        response.setId(requisition.getId());
        response.setStoreRequisitionId(requisition.getStoreRequisitionId());
        response.setDate(requisition.getDate());
        response.setStatus(requisition.getStatus());


        List<StoreRequisitionItemResponse> itemResponses = requisition.getItems().stream().map(item -> {
            StoreRequisitionItemResponse itemResponse = new StoreRequisitionItemResponse();
            itemResponse.setId(item.getId());
            itemResponse.setItemCode(item.getItemCode());
            itemResponse.setItemName(item.getItemName());
            itemResponse.setUnit(item.getUnit());
            itemResponse.setRequiredQuantity(item.getRequiredQuantity());
            itemResponse.setApprovedQuantity(item.getApprovedQuantity());
            itemResponse.setReceivedQuantity(item.getReceivedQuantity());
            itemResponse.setRate(item.getRate());
            itemResponse.setTotal(item.getTotal());
            itemResponse.setGrnNo(item.getGrnNo());
            itemResponse.setReceivedDate(item.getReceivedDate());
            itemResponse.setTransactionId(item.getTransaction() != null ? item.getTransaction().getTid() : null);
            return itemResponse;
        }).toList();

        response.setItems(itemResponses);
        return response;
    }
}

