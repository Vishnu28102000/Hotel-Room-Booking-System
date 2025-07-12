package com.HotelRoomBookingSystem2.HotelRoomBookingSystem2.Repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import com.HotelRoomBookingSystem2.HotelRoomBookingSystem2.Entity.Booking;
import com.HotelRoomBookingSystem2.HotelRoomBookingSystem2.Entity.Users;
@Repository
public interface BookingRepository extends JpaRepository<Booking, UUID> {
    List<Booking> findByUser(Users user);
    
    
}
