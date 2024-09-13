package com.example.backend.service;

import com.example.backend.model.LocalRequest;
import com.example.backend.repository.LocalRequestRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocalRequestService {

    @Autowired
    private LocalRequestRepository localRequestRepository;

    public LocalRequest saveLocalRequest(LocalRequest localRequest) {
        return localRequestRepository.save(localRequest);
    }
    
    public List<LocalRequest> getAllLocalRequests() {
        return localRequestRepository.findAll();
    }
}
