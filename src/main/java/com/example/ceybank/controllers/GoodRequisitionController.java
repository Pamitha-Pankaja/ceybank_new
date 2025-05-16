package com.example.ceybank.controllers;

import com.example.ceybank.models.GoodRequisition;
import com.example.ceybank.models.GoodRequisitionItem;
import com.example.ceybank.models.StoreRequisition;
import com.example.ceybank.responses.*;
import com.example.ceybank.services.GoodRequisitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
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





    @PostMapping("/issue/add-batch")
    public ResponseEntity<String> addItemsToIssueBatch(@RequestBody IssueBatchAssignRequest request) {
        goodRequisitionService.addItemsToIssueBatch(request);
        return ResponseEntity.ok("Items assigned to issue batch successfully");
    }

    @GetMapping("/issue/list/{requisitionId}")
    public ResponseEntity<List<String>> getIssueNos(@PathVariable Long requisitionId) {
        return ResponseEntity.ok(goodRequisitionService.getIssueNosForRequisition(requisitionId));
    }

    @PutMapping("/issue/update-items")
    public ResponseEntity<String> updateIssueItems(@RequestBody IssueItemUpdateRequest request) {
        goodRequisitionService.updateIssuedItems(request.getItems());
        return ResponseEntity.ok("Items updated successfully for issueNo: " + request.getIssueNo());
    }

    @GetMapping("/issue/items/{issueNo}")
    public ResponseEntity<List<GoodRequisitionItemResponse>> getItemsForIssueNo(@PathVariable String issueNo) {
        return ResponseEntity.ok(goodRequisitionService.getItemsByIssueNo(issueNo));
    }




    @GetMapping("/all")
    public ResponseEntity<List<GoodRequisitionResponse>> getAllRequisitions() {
        List<GoodRequisitionResponse> responses = goodRequisitionService.getAllRequisitionResponses();
        return ResponseEntity.ok(responses);
    }


    @GetMapping("/summaries")
    public ResponseEntity<List<GoodRequisitionSummaryResponse>> getRequisitionSummaries() {
        List<GoodRequisitionSummaryResponse> summaries = goodRequisitionService.getAllRequisitionSummaries();
        return ResponseEntity.ok(summaries);
    }


    @GetMapping("/{id}/items")
    public ResponseEntity<List<GoodRequisitionItemResponse>> getRequisitionItems(@PathVariable Long id) {
        List<GoodRequisitionItemResponse> items = goodRequisitionService.getRequisitionItems(id);
        return ResponseEntity.ok(items);
    }




}
