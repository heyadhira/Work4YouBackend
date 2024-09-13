package com.example.backend.controller;

import com.example.backend.model.LocalRequest;
import com.example.backend.service.LocalRequestService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LocalRequestController {

    @Autowired
    private LocalRequestService localRequestService;

    @PostMapping("/local")
    public ResponseEntity<?> submitLocalRequest(@RequestBody LocalRequest localRequest) {
        // Validate the input data here if necessary

        // Save the new local request
        LocalRequest savedLocalRequest = localRequestService.saveLocalRequest(localRequest);
        return ResponseEntity.ok(savedLocalRequest);
    }
    
    @GetMapping("/local")
    public ResponseEntity<?> getAllLocalRequests() {
        List<LocalRequest> localRequests = localRequestService.getAllLocalRequests();
        return ResponseEntity.ok(localRequests);
    }
}
