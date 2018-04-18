package com.management.dto;

import javax.validation.constraints.NotNull;

/**
 * @author Nikola Stojanovic
 *
 */
public class ReservationDTO {

	private int reservationId;
	@NotNull
	private int propsId;
	private int userId;
	
	public int getReservationId() {
		return reservationId;
	}
	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}
	public int getPropsId() {
		return propsId;
	}
	public void setPropsId(int propsId) {
		this.propsId = propsId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
}
