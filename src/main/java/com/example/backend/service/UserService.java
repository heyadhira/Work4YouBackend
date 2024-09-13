package com.example.backend.service;

import com.example.backend.model.User;
import com.example.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Optional<User> login(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        
        if (user.isPresent()) {
            User existingUser = user.get();
            // Check if the password matches
            if (passwordEncoder.matches(password, existingUser.getPassword())) {
                return Optional.of(existingUser);
            }
        }
        return Optional.empty(); // return empty if email or password don't match
    }

    public void register(User user) {
        // Check if the email already exists
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail().toLowerCase());
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("Email already exists"); // Throw an exception if the user already exists
        }
        
        // Encrypt the user's password before saving
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        
        // Save the user to the database
        userRepository.save(user);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email.toLowerCase());
    }
    
    // Add the updateUser method
    public void updateUser(User user) {
        // Update the existing user in the repository
        userRepository.save(user); 
}
    public List<User> findAll() {
        return userRepository.findAll(); // Assuming you're using a JPA repository
    }
    
}
