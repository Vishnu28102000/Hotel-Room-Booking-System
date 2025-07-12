package com.HotelRoomBookingSystem2.HotelRoomBookingSystem2.Services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.HotelRoomBookingSystem2.HotelRoomBookingSystem2.Entity.Room;
import com.HotelRoomBookingSystem2.HotelRoomBookingSystem2.Repository.RoomRepository;

@Service
public class RoomService {
	
	public RoomService(RoomRepository roomRepository) {
		super();
		this.roomRepository = roomRepository;
	}

	private final RoomRepository roomRepository;

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Room addRoom(Room room) {
       
        return  roomRepository.save(room);
    }

   
    public void deleteRoom(UUID roomId) {
        roomRepository.deleteById(roomId);
    }

}
