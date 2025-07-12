package com.HotelRoomBookingSystem2.HotelRoomBookingSystem2.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HotelRoomBookingSystem2.HotelRoomBookingSystem2.Entity.Room;
import com.HotelRoomBookingSystem2.HotelRoomBookingSystem2.Services.RoomService;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    public RoomController(RoomService roomService) {
		super();
		this.roomService = roomService;
	}

	private final RoomService roomService;

    @GetMapping
    @Secured({"ROLE_ADMIN","ROLE_USER"})
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

    @PostMapping
    @Secured({"ROLE_ADMIN","ROLE_USER"})
    public Room addRoom(@RequestBody Room room) {
        return roomService.addRoom(room);
    }

   
    @DeleteMapping("/{roomId}")
    @Secured({"ROLE_ADMIN","ROLE_USER"})
    public String deleteRoom(@PathVariable UUID roomId) {
        roomService.deleteRoom(roomId);
        return "Room deleted successfully";
    }
}
