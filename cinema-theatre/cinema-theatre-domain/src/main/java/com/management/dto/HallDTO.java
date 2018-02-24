package com.management.dto;

import com.management.entities.CinemaTheatre;

/**
 * @author Zivko Stanisic
 *
 */
public class HallDTO {
	
	private int hallId;
	private CinemaTheatre cinemaTheatre;
	private String hallName;
	
	public int getHallId() {
		return hallId;
	}
	public void setHallId(int hallId) {
		this.hallId = hallId;
	}
	public CinemaTheatre getCinemaTheatre() {
		return cinemaTheatre;
	}
	public void setCinemaTheatre(CinemaTheatre cinemaTheatre) {
		this.cinemaTheatre = cinemaTheatre;
	}
	public String getHallName() {
		return hallName;
	}
	public void setHallName(String hallName) {
		this.hallName = hallName;
	}

}
