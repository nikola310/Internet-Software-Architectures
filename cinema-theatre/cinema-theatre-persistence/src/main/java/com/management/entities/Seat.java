package com.management.entities;

// Generated Apr 2, 2018 4:06:14 PM by Hibernate Tools 4.3.1

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Seat generated by hbm2java
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "seat", catalog = "isa")
public class Seat implements java.io.Serializable {

	private int seatId;
	private Hall hall;
	private User user;
	private boolean seatTaken;
	private Date seatModified;

	public Seat() {
	}

	public Seat(int seatId, Hall hall, boolean seatTaken, Date seatModified) {
		this.seatId = seatId;
		this.hall = hall;
		this.seatTaken = seatTaken;
		this.seatModified = seatModified;
	}

	public Seat(int seatId, Hall hall, User user, boolean seatTaken,
			Date seatModified) {
		this.seatId = seatId;
		this.hall = hall;
		this.user = user;
		this.seatTaken = seatTaken;
		this.seatModified = seatModified;
	}

	@Id
	@Column(name = "SEAT_ID", unique = true, nullable = false)
	public int getSeatId() {
		return this.seatId;
	}

	public void setSeatId(int seatId) {
		this.seatId = seatId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "HALL_ID", nullable = false)
	public Hall getHall() {
		return this.hall;
	}

	public void setHall(Hall hall) {
		this.hall = hall;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "SEAT_TAKEN", nullable = false)
	public boolean isSeatTaken() {
		return this.seatTaken;
	}

	public void setSeatTaken(boolean seatTaken) {
		this.seatTaken = seatTaken;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SEAT_MODIFIED", nullable = false, length = 19)
	public Date getSeatModified() {
		return this.seatModified;
	}

	public void setSeatModified(Date seatModified) {
		this.seatModified = seatModified;
	}

}
