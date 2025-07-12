package com.HotelRoomBookingSystem2.HotelRoomBookingSystem2.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HotelRoomBookingSystem2.HotelRoomBookingSystem2.Entity.Users;
import com.HotelRoomBookingSystem2.HotelRoomBookingSystem2.Repository.UserRepository;
import com.HotelRoomBookingSystem2.HotelRoomBookingSystem2.jwt.JwtUtil;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    public Users register(Users user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }
        return userRepository.save(user);
    }

    public String login(Users loginRequest) {
        Optional<Users> optionalUser = userRepository.findByEmail(loginRequest.getEmail());
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        Users user = optionalUser.get();
        if (!user.getPassword().equals(loginRequest.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        return jwtUtil.generateToken(user.getEmail(), user.getRole().name());
    }
}