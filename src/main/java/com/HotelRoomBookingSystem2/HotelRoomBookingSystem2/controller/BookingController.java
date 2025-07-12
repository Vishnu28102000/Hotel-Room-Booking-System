package com.HotelRoomBookingSystem2.HotelRoomBookingSystem2.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HotelRoomBookingSystem2.HotelRoomBookingSystem2.Entity.Booking;
import com.HotelRoomBookingSystem2.HotelRoomBookingSystem2.Entity.Room;
import com.HotelRoomBookingSystem2.HotelRoomBookingSystem2.Services.BookingService;
@RestController
@RequestMapping("/api/booking")
public class BookingController {
	private final BookingService bookingService;

    @GetMapping("/available")
    @Secured({"ROLE_ADMIN","ROLE_USER"})
    public List<Room> getAvailableRooms() {
        return bookingService.getAvailableRooms();
    }

    @PostMapping("/{id}")
    @Secured({"ROLE_ADMIN","ROLE_USER"})
    public Booking bookRoom(@RequestBody Booking booking, Principal principal) {
        return bookingService.bookRoom(booking, principal.getName());
    }

    @GetMapping("/history")
    @Secured({"ROLE_ADMIN","ROLE_USER"})
    public List<Booking> getBookingHistory(Principal principal) {
        return bookingService.getBookingHistory(principal.getName());
    }

	public BookingController(BookingService bookingService) {
		super();
		this.bookingService = bookingService;
	}

}
