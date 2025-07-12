package com.HotelRoomBookingSystem2.HotelRoomBookingSystem2.Services;

import java.io.ObjectInputFilter.Status;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.HotelRoomBookingSystem2.HotelRoomBookingSystem2.Entity.Booking;
import com.HotelRoomBookingSystem2.HotelRoomBookingSystem2.Entity.Booking.BookingStatus;
import com.HotelRoomBookingSystem2.HotelRoomBookingSystem2.Entity.Room;
import com.HotelRoomBookingSystem2.HotelRoomBookingSystem2.Entity.Users;
import com.HotelRoomBookingSystem2.HotelRoomBookingSystem2.Repository.BookingRepository;
import com.HotelRoomBookingSystem2.HotelRoomBookingSystem2.Repository.RoomRepository;
import com.HotelRoomBookingSystem2.HotelRoomBookingSystem2.Repository.UserRepository;
@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;
    private final RedisTemplate<String, Object> redisTemplate;

    private static final String AVAILABLE_ROOMS_CACHE = "available_rooms";

    public BookingService(BookingRepository bookingRepository,
                          RoomRepository roomRepository,
                          UserRepository userRepository,
                          RedisTemplate<String, Object> redisTemplate) {
        this.bookingRepository = bookingRepository;
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
        this.redisTemplate = redisTemplate;
    }

    public List<Room> getAvailableRooms() {
        List<Room> cached = (List<Room>) redisTemplate.opsForValue().get(AVAILABLE_ROOMS_CACHE);
        if (cached != null) {
            return cached;
        }

        List<Room> availableRooms = roomRepository.findAll().stream()
                .filter(Room::isAvailable)
                .toList();

        redisTemplate.opsForValue().set(AVAILABLE_ROOMS_CACHE, availableRooms);
        return availableRooms;
    }

    public Booking bookRoom(Booking request, String email) {
    	
    	Optional<Room> optionalRoom = roomRepository.findById(request.getId());

    	if (optionalRoom.isEmpty()) {
    	    throw new RuntimeException("Room not found");
    	}

    	Room room = optionalRoom.get();

    	if (!room.isAvailable()) {
    	    throw new RuntimeException("Room is not available");
    	}

    	Optional<Users> user = userRepository.findByEmail(email);

    	if (user == null) {
    	    throw new RuntimeException("User not found");
    	}

    	       
        Booking booking = new Booking();
                
booking.setBookingDate(LocalDate.now());
booking.setId(booking.getId());
booking.setRoom(room);
booking.setStatus(BookingStatus.CONFIRMED);
       
        roomRepository.save(room);
        bookingRepository.save(booking);

        redisTemplate.delete(AVAILABLE_ROOMS_CACHE); 

        return booking;
    }

    public List<Booking> getBookingHistory(String userEmail) {
        Optional<Users> user = userRepository.findByEmail(userEmail);
                
        return bookingRepository.findAll();
    }
}
