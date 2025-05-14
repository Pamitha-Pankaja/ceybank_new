package com.example.ceybank.services;

import com.example.ceybank.models.*;
import com.example.ceybank.repositories.GoodRequisitionItemRepository;
import com.example.ceybank.repositories.GoodRequisitionRepository;
import com.example.ceybank.repositories.InventoryItemRepository;
import com.example.ceybank.repositories.TransactionRepository;
import com.example.ceybank.responses.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class GoodRequisitionService {

    @Autowired
    GoodRequisitionRepository goodRequisitionRepository;

    @Autowired
    InventoryItemRepository inventoryItemRepository;

    @Autowired
    GoodRequisitionItemRepository goodRequisitionItemRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Transactional
    public GoodRequisition createGoodRequisition(GoodRequisition requisition) {
        for (GoodRequisitionItem item : requisition.getItems()) {
            InventoryItem inventoryItem = inventoryItemRepository
                    .findByItemCode(item.getItemCode())
                    .orElseThrow(() -> new RuntimeException("Item code not found: " + item.getItemCode()));

            item.setGoodRequisition(requisition);
            item.setInventoryItem(inventoryItem);
        }

        return goodRequisitionRepository.save(requisition); // only basic data saved here
    }

    public void approveItem(ApproveGoodRequisitionItemRequest request) {
        GoodRequisitionItem item = goodRequisitionItemRepository.findById(request.getItemId())
                .orElseThrow(() -> new RuntimeException("Store Requisition Item not found"));
        item.setApprovedQuantity(request.getApprovedQuantity());
        goodRequisitionItemRepository.save(item);
    }


    @Transactional
    public void issueBatch(ReceiveGoodRequisitionBatchRequest request) {
        for (ReceiveGoodRequisitionItemRequest itemRequest : request.getItems()) {
            GoodRequisitionItem item = goodRequisitionItemRepository.findById(itemRequest.getItemId())
                    .orElseThrow(() -> new RuntimeException("Item not found"));

            item.setIssuedQuantity(itemRequest.getIssuedQuantity());
            item.setIssueNo(request.getIssueNo()); // Set common GRN number
            item.setReceivedDate(request.getReceivedDate());

            InventoryItem inventoryItem = item.getInventoryItem();

            // Calculate new balance before saving transaction
            int newBalance = inventoryItem.getQuantity() - itemRequest.getIssuedQuantity();

            // Create transaction with balance
            Transaction transaction = new Transaction();
            transaction.setInventoryItem(inventoryItem);
            transaction.setGoodRequisitionItem(item);
            transaction.setIssuedQuantity(itemRequest.getIssuedQuantity());
            transaction.setDate(LocalDate.now());
            transaction.setBalance(newBalance); // ✅ NEW LINE


            transactionRepository.save(transaction);
            item.setTransaction(transaction);

            // Update inventory quantity
            // InventoryItem inventoryItem = item.getInventoryItem();
            //inventoryItem.setQuantity(inventoryItem.getQuantity() + itemRequest.getReceivedQuantity());
            inventoryItem.setQuantity(newBalance);
            inventoryItemRepository.save(inventoryItem);

            goodRequisitionItemRepository.save(item);
        }
    }




    public GoodRequisitionResponse refactorResponse(GoodRequisition requisition) {
        GoodRequisitionResponse response = new GoodRequisitionResponse();
        response.setId(requisition.getId());
        response.setGoodRequisitionId(requisition.getGoodRequisitionId());
        response.setDate(requisition.getDate());

        List<GoodRequisitionItemResponse> itemResponses = requisition.getItems().stream().map(item -> {
            GoodRequisitionItemResponse itemResponse = new GoodRequisitionItemResponse();
            itemResponse.setId(item.getId());
            itemResponse.setItemCode(item.getItemCode());
            itemResponse.setItemName(item.getItemName());
            itemResponse.setUnit(item.getUnit());
            itemResponse.setRequiredQuantity(item.getRequiredQuantity());
 //           itemResponse.setApprovedQuantity(item.getApprovedQuantity());
//            itemResponse.setReceivedQuantity(item.getReceivedQuantity());
//            itemResponse.setRate(item.getRate());
//            itemResponse.setTotal(item.getTotal());
              itemResponse.setIssueNo(item.getIssueNo());
              itemResponse.setReceivedDate(item.getReceivedDate());
 //           itemResponse.setTransactionId(item.getTransaction() != null ? item.getTransaction().getTid() : null);
            return itemResponse;
        }).toList();

        response.setItems(itemResponses);
        return response;
    }




    @Transactional
    public void addItemsToIssueBatch(IssueBatchAssignRequest request) {
        for (Long itemId : request.getItemIds()) {
            GoodRequisitionItem item = goodRequisitionItemRepository.findById(itemId)
                    .orElseThrow(() -> new RuntimeException("Item not found"));
            item.setIssueNo(request.getIssueNo());
            item.setReceivedDate(request.getIssuedDate());
            goodRequisitionItemRepository.save(item);
        }
    }


    public List<String> getIssueNosForRequisition(Long requisitionId) {
        return goodRequisitionItemRepository.findDistinctIssueNosByGoodRequisitionId(requisitionId);
    }


    @Transactional
    public void updateIssuedItems(List<ReceiveGoodRequisitionItemRequest> items) {
        for (ReceiveGoodRequisitionItemRequest itemRequest : items) {
            GoodRequisitionItem item = goodRequisitionItemRepository.findById(itemRequest.getItemId())
                    .orElseThrow(() -> new RuntimeException("Item not found"));

            item.setIssuedQuantity(itemRequest.getIssuedQuantity());

            InventoryItem inventoryItem = item.getInventoryItem();
            int newBalance = inventoryItem.getQuantity() - itemRequest.getIssuedQuantity();

            Transaction transaction = new Transaction();
            transaction.setInventoryItem(inventoryItem);
            transaction.setGoodRequisitionItem(item);
            transaction.setIssuedQuantity(itemRequest.getIssuedQuantity());
            transaction.setDate(LocalDate.now());
            transaction.setBalance(newBalance);

            transactionRepository.save(transaction);
            item.setTransaction(transaction);
            inventoryItem.setQuantity(newBalance);
            inventoryItemRepository.save(inventoryItem);
            goodRequisitionItemRepository.save(item);
        }
    }

    public List<GoodRequisitionItem> getItemsByIssueNo(String issueNo) {
        return goodRequisitionItemRepository.findByIssueNo(issueNo);
    }



    public List<GoodRequisitionResponse> getAllRequisitionResponses() {
        List<GoodRequisition> requisitions = goodRequisitionRepository.findAll();

        return requisitions.stream()
                .map(this::refactorResponse)
                .toList();
    }


    public List<GoodRequisitionSummaryResponse> getAllRequisitionSummaries() {
        List<GoodRequisition> requisitions = goodRequisitionRepository.findAll();

        return requisitions.stream().map(requisition -> {
            GoodRequisitionSummaryResponse summary = new GoodRequisitionSummaryResponse();
            summary.setId(requisition.getId());
            summary.setGoodRequisitionId(requisition.getGoodRequisitionId());
            summary.setDate(requisition.getDate());
            summary.setStatus(requisition.getStatus());
            return summary;
        }).toList();
    }


}
