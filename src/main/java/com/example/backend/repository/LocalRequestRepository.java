package com.example.backend.repository;

import com.example.backend.model.LocalRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LocalRequestRepository extends MongoRepository<LocalRequest, String> {
}
