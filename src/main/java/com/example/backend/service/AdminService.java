package com.example.backend.service;

import org.springframework.stereotype.Service;

@Service
public class AdminService {

    // Hardcoded admin credentials
    private static final String ADMIN_EMAIL = "oak.work4you@gmail.com";
    private static final String ADMIN_PASSWORD = "Admin@w4u";

    public boolean loginAdmin(String email, String password) {
        // Check if the provided email and password match the hardcoded credentials
        return ADMIN_EMAIL.equals(email) && ADMIN_PASSWORD.equals(password);
    }
}
