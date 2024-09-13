package com.example.backend.controller;

import com.example.backend.model.HireRequest;
import com.example.backend.service.HireRequestService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class HireRequestController {

    @Autowired
    private HireRequestService hireRequestService;

    @PostMapping("/hire")
    public ResponseEntity<?> submitHireRequest(@RequestBody HireRequest hireRequest) {
        HireRequest savedHireRequest = hireRequestService.saveHireRequest(hireRequest);
        return ResponseEntity.ok(savedHireRequest);
    }
    
    @GetMapping("/hire")
    public ResponseEntity<List<HireRequest>> getAllHireRequests() {
        List<HireRequest> hireRequests = hireRequestService.getAllHireRequests();
        return ResponseEntity.ok(hireRequests);
    }
}