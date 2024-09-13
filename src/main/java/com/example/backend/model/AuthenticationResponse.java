package com.example.backend.model;

public class AuthenticationResponse {
    private String token;
    private String name; // New field for user name

    public AuthenticationResponse(String token, String name) {
        this.token = token;
        this.name = name; // Initialize the name
    }

    // Getters and setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
