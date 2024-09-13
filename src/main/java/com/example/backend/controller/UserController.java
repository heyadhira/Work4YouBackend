package com.example.backend.controller;

import com.example.backend.config.JwtUtil;
import com.example.backend.model.AuthenticationRequest;
import com.example.backend.model.AuthenticationResponse;
import com.example.backend.model.User;
import com.example.backend.service.UserService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/auth")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody User user) {
        try {
            userService.register(user);
            logger.info("User registered successfully: {}", user.getEmail());
            return ResponseEntity.ok(Map.of("message", "User registered successfully"));
        } catch (Exception e) {
            logger.error("Registration error: ", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest authRequest) {
        try {
            Optional<User> existingUser = userService.login(authRequest.getEmail(), authRequest.getPassword());
            if (existingUser.isPresent()) {
                String token = jwtUtil.generateToken(existingUser.get().getEmail());
                logger.info("User logged in successfully: {}", existingUser.get().getEmail());
                return ResponseEntity.ok(new AuthenticationResponse(token, existingUser.get().getName())); // Include user name
            } else {
                logger.warn("Login attempt with invalid credentials for email: {}", authRequest.getEmail());
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                                     .body(Map.of("error", "Invalid credentials"));
            }
        } catch (Exception e) {
            logger.error("Login error: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(Map.of("error", e.getMessage()));
        }
    }

    // New method to get authenticated user's data
    @GetMapping("/getdata")
    public ResponseEntity<?> getData() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String email = authentication.getName();
            logger.info("Authenticated user email: {}", email); // Improved logging
            
            Optional<User> user = userService.findByEmail(email);
            if (user.isPresent()) {
                return ResponseEntity.ok(user.get()); // Return user details
            } else {
                logger.warn("User not found for email: {}", email);
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                     .body(Map.of("error", "User not found"));
            }
        } catch (Exception e) {
            logger.error("Error retrieving user data: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(Map.of("error", e.getMessage()));
        }
    }
    
    // New method to update user details
    @PutMapping("/edit")
    public ResponseEntity<?> updateUser(@RequestBody User updatedUser) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String email = authentication.getName();
            
            Optional<User> existingUser = userService.findByEmail(email);
            if (existingUser.isPresent()) {
                User user = existingUser.get();
                user.setName(updatedUser.getName());
                user.setEmail(updatedUser.getEmail());
                user.setPhone(updatedUser.getPhone());
                user.setPassword(updatedUser.getPassword());
                user.setAddress(updatedUser.getAddress());
                user.setCity(updatedUser.getCity());
                user.setState(updatedUser.getState());
                
                userService.updateUser(user); // Update the user in the service layer
                
                logger.info("User updated successfully: {}", email);
                return ResponseEntity.ok(Map.of("message", "User updated successfully"));
            } else {
                logger.warn("User not found for email: {}", email);
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                     .body(Map.of("error", "User not found"));
            }
        } catch (Exception e) {
            logger.error("Error updating user data: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(Map.of("error", e.getMessage()));
        }
    }


    @GetMapping("/test/{email}")
    public ResponseEntity<?> testUserRetrieval(@PathVariable String email) {
        Optional<User> user = userService.findByEmail(email);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            logger.warn("Test user not found for email: {}", email);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body(Map.of("error", "User not found"));
        }
    }
    
    @GetMapping("/users") // New endpoint to get all users
    public ResponseEntity<?> getAllUsers() {
        try {
            List<User> users = userService.findAll(); // Make sure to implement this method in your service
            if (users.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                     .body(Map.of("error", "No users found"));
            } else {
                return ResponseEntity.ok(users); // Return the list of users
            }
        } catch (Exception e) {
            logger.error("Error retrieving users data: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(Map.of("error", e.getMessage()));
        }
    }
    
}
