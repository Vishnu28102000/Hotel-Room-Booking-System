package com.HotelRoomBookingSystem2.HotelRoomBookingSystem2.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.HotelRoomBookingSystem2.HotelRoomBookingSystem2.Entity.Room;
@Repository
public interface RoomRepository extends JpaRepository<Room, UUID> {
}
