package com.HotelRoomBookingSystem2.HotelRoomBookingSystem2.Entity;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "rooms")
public class Room {
	
    

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID roomid;

    public UUID getRoomid() {
		return roomid;
	}

	public void setRoomid(UUID roomid) {
		this.roomid = roomid;
	}

	private String type;

    private double price;

    private boolean isAvailable;

}

