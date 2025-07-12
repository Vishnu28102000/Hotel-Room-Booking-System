package com.HotelRoomBookingSystem2.HotelRoomBookingSystem2.Repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.HotelRoomBookingSystem2.HotelRoomBookingSystem2.Entity.Users;
@Repository
public interface UserRepository extends JpaRepository<Users, UUID> {
    Optional<Users> findByEmail(String email);

	
}
