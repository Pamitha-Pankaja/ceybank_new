package com.example.ceybank.controllers;

import com.example.ceybank.models.StoreRequisition;
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

    @PostMapping("/add")
    public ResponseEntity<StoreRequisitionResponse> createStoreRequisition(@RequestBody StoreRequisition requisition) {
        StoreRequisition saved = storeRequisitionService.createStoreRequisition(requisition);
        StoreRequisitionResponse response = storeRequisitionService.refactorResponse(saved);
        return ResponseEntity.ok(response);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<StoreRequisition> getById(@PathVariable Long id) {
//        return ResponseEntity.ok(storeRequisitionService.getById(id));
//    }

    @GetMapping("/{id}")
    public ResponseEntity<StoreRequisitionResponse> getById(@PathVariable Long id) {
        StoreRequisition requisition = storeRequisitionService.getById(id);
        StoreRequisitionResponse response = storeRequisitionService.refactorResponse(requisition);
        return ResponseEntity.ok(response);
    }
}

