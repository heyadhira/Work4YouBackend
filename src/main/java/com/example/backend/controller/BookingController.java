package com.example.backend.controller;

import com.example.backend.model.Booking;
import com.example.backend.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;

    @PostMapping("/book-now")
    public ResponseEntity<String> createBooking(@RequestBody Booking booking) {
        try {
            bookingRepository.save(booking);
            return new ResponseEntity<>("Booking successful", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred while booking", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/about")
    public ResponseEntity<Booking> getAboutPage() {
        // Logic to fetch booking details or user data
        return new ResponseEntity<>(new Booking(), HttpStatus.OK); // Return an empty Booking for demo
    }
}
