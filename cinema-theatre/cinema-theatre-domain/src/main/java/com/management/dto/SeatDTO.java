package com.management.dto;

import java.util.Date;

import com.management.entities.Hall;
import com.management.entities.User;

/**
 * @author Zivko Stanisic
 *
 */
public class SeatDTO {
	
	private int seatId;
	private Hall hall;
	private User user;
	private boolean seatTaken;
	private Date seatModified;
	
	public int getSeatId() {
		return seatId;
	}
	public void setSeatId(int seatId) {
		this.seatId = seatId;
	}
	public Hall getHall() {
		return hall;
	}
	public void setHall(Hall hall) {
		this.hall = hall;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public boolean isSeatTaken() {
		return seatTaken;
	}
	public void setSeatTaken(boolean seatTaken) {
		this.seatTaken = seatTaken;
	}
	public Date getSeatModified() {
		return seatModified;
	}
	public void setSeatModified(Date seatModified) {
		this.seatModified = seatModified;
	}
}
