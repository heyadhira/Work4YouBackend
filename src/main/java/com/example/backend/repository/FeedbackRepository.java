package com.example.backend.repository;

import com.example.backend.model.Feedback;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FeedbackRepository extends MongoRepository<Feedback, String> {
    // You can define custom query methods here if needed
}
