package com.management.dto;

import java.util.Date;

/**
 * @author Zivko Stanisic
 *
 */
public class SeatDTO {
	
	private int seatId;
	private boolean seatTaken;
	private Date seatModified;
	
	public int getSeatId() {
		return seatId;
	} 
	
	public void setSeatId(int seatId) {
		this.seatId = seatId;
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
