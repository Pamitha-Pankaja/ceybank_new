package com.example.ceybank.controllers;

import com.example.ceybank.models.StoreRequisition;
import com.example.ceybank.responses.*;
import com.example.ceybank.services.StoreRequisitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/store-requisitions")
public class StoreRequisitionController {

    @Autowired
    private StoreRequisitionService storeRequisitionService;

//    @PostMapping("/add")
//    public ResponseEntity<StoreRequisition> createStoreRequisition(@RequestBody StoreRequisition requisition) {
//        StoreRequisition saved = storeRequisitionService.createStoreRequisition(requisition);
//        return ResponseEntity.ok(saved);
//    }

//    @PostMapping("/add")
//    public ResponseEntity<StoreRequisitionResponse> createStoreRequisition(@RequestBody StoreRequisition requisition) {
//        StoreRequisition saved = storeRequisitionService.createStoreRequisition(requisition);
//        StoreRequisitionResponse response = storeRequisitionService.refactorResponse(saved);
//        return ResponseEntity.ok(response);
//    }

//    @GetMapping("/{id}")
//    public ResponseEntity<StoreRequisition> getById(@PathVariable Long id) {
//        return ResponseEntity.ok(storeRequisitionService.getById(id));
//    }

    // Create basic requisition
    @PostMapping("/add")
    public ResponseEntity<StoreRequisitionResponse> createStoreRequisition(@RequestBody StoreRequisition requisition) {
        StoreRequisition saved = storeRequisitionService.createStoreRequisition(requisition);
        StoreRequisitionResponse response = storeRequisitionService.refactorResponse(saved);
        return ResponseEntity.ok(response);
    }

    // Manager approves quantity
    @PutMapping("/approve-item")
    public ResponseEntity<String> approveItem(@RequestBody ApproveStoreRequisitionItemRequest request) {
        storeRequisitionService.approveItem(request);
        return ResponseEntity.ok("Item approved successfully");
    }

    // Storekeeper receives item
//    @PutMapping("/receive-item")
//    public ResponseEntity<String> receiveItem(@RequestBody ReceiveStoreRequisitionItemRequest request) {
//        storeRequisitionService.receiveItem(request);
//        return ResponseEntity.ok("Item received and transaction created");
//    }

    @PutMapping("/receive-batch")
    public ResponseEntity<String> receiveBatch(@RequestBody ReceiveStoreRequisitionBatchRequest request) {
        storeRequisitionService.receiveBatch(request);
        return ResponseEntity.ok("Batch received successfully with GRN: " + request.getGrnNo());
    }

    @PutMapping("/approve-status/{id}")
    public ResponseEntity<String> approveRequisitionStatus(@PathVariable Long id) {
        storeRequisitionService.approveRequisitionStatus(id);
        return ResponseEntity.ok("Store requisition approved successfully");
    }


    // Get all store requisitions
    @GetMapping("/all")
    public ResponseEntity<List<StoreRequisitionResponse>> getAllRequisitions() {
        List<StoreRequisitionResponse> responses = storeRequisitionService.getAllRequisitionResponses();
        return ResponseEntity.ok(responses);
    }

    // Get items for a specific requisition
    @GetMapping("/{id}/items")
    public ResponseEntity<List<StoreRequisitionItemResponse>> getRequisitionItems(@PathVariable Long id) {
        List<StoreRequisitionItemResponse> items = storeRequisitionService.getRequisitionItems(id);
        return ResponseEntity.ok(items);
    }


    @GetMapping("/summaries")
    public ResponseEntity<List<StoreRequisitionSummaryResponse>> getRequisitionSummaries() {
        List<StoreRequisitionSummaryResponse> summaries = storeRequisitionService.getAllRequisitionSummaries();
        return ResponseEntity.ok(summaries);
    }




}

