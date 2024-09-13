package com.example.backend.controller;

import com.example.backend.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/signin")
    public ResponseEntity<?> loginAdmin(@RequestBody AdminLoginRequest adminLoginRequest) {
        boolean isLoggedIn = adminService.loginAdmin(adminLoginRequest.getEmail(), adminLoginRequest.getPassword());

        if (isLoggedIn) {
            return ResponseEntity.ok("Login Successful");
        } else {
            return ResponseEntity.status(400).body("Invalid Credentials");
        }
    }

    // DTO class for login request
    public static class AdminLoginRequest {
        private String email;
        private String password;

        // Getters and Setters
        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
