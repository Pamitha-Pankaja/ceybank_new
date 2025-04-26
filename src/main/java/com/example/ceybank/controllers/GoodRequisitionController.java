package com.example.ceybank.controllers;

import com.example.ceybank.models.GoodRequisition;
import com.example.ceybank.models.StoreRequisition;
import com.example.ceybank.responses.*;
import com.example.ceybank.services.GoodRequisitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/good-requisitions")
public class GoodRequisitionController {

    @Autowired
    GoodRequisitionService goodRequisitionService;

//    @PostMapping("/add")
//    public ResponseEntity<GoodRequisition> createGoodRequisition(@RequestBody GoodRequisition requisition) {
//        GoodRequisition saved = goodRequisitionService.createGoodRequisition(requisition);
//        return ResponseEntity.ok(saved);
//    }

    @PostMapping("/add")
    public ResponseEntity<GoodRequisitionResponse> createStoreRequisition(@RequestBody GoodRequisition requisition) {
        GoodRequisition saved = goodRequisitionService.createGoodRequisition(requisition);
        GoodRequisitionResponse response = goodRequisitionService.refactorResponse(saved);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/approve-item")
    public ResponseEntity<String> approveItem(@RequestBody ApproveGoodRequisitionItemRequest request) {
        goodRequisitionService.approveItem(request);
        return ResponseEntity.ok("Item approved successfully");
    }

    @PutMapping("/issue-batch")
    public ResponseEntity<String> issueBatch(@RequestBody ReceiveGoodRequisitionBatchRequest request) {
        goodRequisitionService.issueBatch(request);
        return ResponseEntity.ok("Batch received successfully with GRN: " + request.getIssueNo());
    }

}
