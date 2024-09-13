package com.example.backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalTime;

@Document(collection = "hire_requests")
public class HireRequest {

    @Id
    private String id;
    private String name;
    private String email;
    private String altEmail;
    private String phone;
    private String altPhone;
    private double amount;
    private String state;
    private String city;

    @JsonFormat(pattern = "HH:mm") // Specify the format for stime
    private LocalTime stime;

    @JsonFormat(pattern = "HH:mm") // Specify the format for etime
    private LocalTime etime;

    private String date;
    private String address;
    private String service;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getAltEmail() {
        return altEmail;
    }
    public void setAltEmail(String altEmail) {
        this.altEmail = altEmail;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getAltPhone() {
        return altPhone;
    }
    public void setAltPhone(String altPhone) {
        this.altPhone = altPhone;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public LocalTime getStime() {
        return stime;
    }
    public void setStime(LocalTime stime) {
        this.stime = stime;
    }
    public LocalTime getEtime() {
        return etime;
    }
    public void setEtime(LocalTime etime) {
        this.etime = etime;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getService() {
        return service;
    }
    public void setService(String service) {
        this.service = service;
    }
}
