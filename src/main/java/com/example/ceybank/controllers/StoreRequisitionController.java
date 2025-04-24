package com.example.ceybank.controllers;

import com.example.ceybank.models.StoreRequisition;
import com.example.ceybank.responses.ApproveStoreRequisitionItemRequest;
import com.example.ceybank.responses.ReceiveStoreRequisitionBatchRequest;
import com.example.ceybank.responses.ReceiveStoreRequisitionItemRequest;
import com.example.ceybank.responses.StoreRequisitionResponse;
import com.example.ceybank.services.StoreRequisitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

}

