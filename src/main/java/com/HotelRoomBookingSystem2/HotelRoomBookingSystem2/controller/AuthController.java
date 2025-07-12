package com.HotelRoomBookingSystem2.HotelRoomBookingSystem2.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HotelRoomBookingSystem2.HotelRoomBookingSystem2.Entity.Users;
import com.HotelRoomBookingSystem2.HotelRoomBookingSystem2.Services.AuthService;

import jakarta.validation.Valid;
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

   
    @PostMapping("/register")
    public ResponseEntity<Users> register(@Valid @RequestBody Users user) {
        Users registeredUser = authService.register(user);
        return ResponseEntity.ok(registeredUser);
    }

  
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Users user) {
        String token = authService.login(user);
        return ResponseEntity.ok(token);
    }

    
}
