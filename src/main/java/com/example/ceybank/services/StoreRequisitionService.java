package com.example.ceybank.services;

import com.example.ceybank.responses.StoreRequisitionItemResponse;
import com.example.ceybank.responses.StoreRequisitionResponse;
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

//    @Transactional
//    public StoreRequisition createStoreRequisition(StoreRequisition requisition) {
//
//        for (StoreRequisitionItem item : requisition.getItems()) {
//
//            InventoryItem inventoryItem = inventoryItemRepository
//                    .findByItemCode(item.getItemCode())
//                    .orElseThrow(() -> new RuntimeException("Item code not found: " + item.getItemCode()));
//
//            // Set bi-directional relationships
//            item.setStoreRequisition(requisition);
//            item.setInventoryItem(inventoryItem);
//
//            // === Auto-create transaction if receivedQuantity > 0 ===
//            if (item.getReceivedQuantity() > 0) {
//                Transaction transaction = new Transaction();
//                transaction.setInventoryItem(inventoryItem);
//                transaction.setStoreRequisitionItem(item);
//                transaction.setReceivedQuantity(item.getReceivedQuantity());
//                transaction.setDate(LocalDate.now());
//                transactionRepository.save(transaction);
//
//                // === Update inventory quantity ===
//                int updatedQty = inventoryItem.getQuantity() + item.getReceivedQuantity();
//                inventoryItem.setQuantity(updatedQty);
//                inventoryItemRepository.save(inventoryItem);
//
//                // Link transaction to item
//                item.setTransaction(transaction);
//            }
//        }
//
//        return storeRequisitionRepository.save(requisition);
//    }


    @Transactional
    public StoreRequisition createStoreRequisition(StoreRequisition requisition) {

        // Set parent in each child item first
        for (StoreRequisitionItem item : requisition.getItems()) {
            InventoryItem inventoryItem = inventoryItemRepository
                    .findByItemCode(item.getItemCode())
                    .orElseThrow(() -> new RuntimeException("Item code not found: " + item.getItemCode()));

            item.setStoreRequisition(requisition);
            item.setInventoryItem(inventoryItem);
        }

        // Save StoreRequisition and its items (thanks to CascadeType.ALL)
        StoreRequisition savedRequisition = storeRequisitionRepository.save(requisition);

        // Now loop again for each item and handle Transaction creation
        for (StoreRequisitionItem item : savedRequisition.getItems()) {
            if (item.getReceivedQuantity() > 0) {
                Transaction transaction = new Transaction();
                transaction.setInventoryItem(item.getInventoryItem());
                transaction.setStoreRequisitionItem(item);
                transaction.setReceivedQuantity(item.getReceivedQuantity());
                transaction.setDate(LocalDate.now());

                // Save transaction
                transactionRepository.save(transaction);

                // Update inventory quantity
                InventoryItem inventoryItem = item.getInventoryItem();
                inventoryItem.setQuantity(inventoryItem.getQuantity() + item.getReceivedQuantity());
                inventoryItemRepository.save(inventoryItem);

                // Link back to item
                item.setTransaction(transaction);
            }
        }

        return savedRequisition;
    }





    public StoreRequisition getById(Long id) {
        return storeRequisitionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Store Requisition not found: " + id));
    }

    public StoreRequisitionResponse refactorResponse(StoreRequisition requisition) {
        StoreRequisitionResponse response = new StoreRequisitionResponse();
        response.setId(requisition.getId());
        response.setStoreRequisitionId(requisition.getStoreRequisitionId());
        response.setDate(requisition.getDate());

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

