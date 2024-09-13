package com.example.backend.repository;

import com.example.backend.model.HireRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HireRequestRepository extends MongoRepository<HireRequest, String> {
}
