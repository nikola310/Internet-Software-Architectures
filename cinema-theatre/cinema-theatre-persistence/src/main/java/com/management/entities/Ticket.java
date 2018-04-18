package com.management.entities;
// Generated Apr 18, 2018 12:50:36 AM by Hibernate Tools 5.2.8.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * Ticket generated by hbm2java
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "ticket", catalog = "isa")
public class Ticket implements java.io.Serializable {

	private int ticId;
	private Event event;
	private Seat seat;
	private User user;
	private float ticPrice;
	private int ticDiscount;
	private boolean ticSold;

	public Ticket() {
	}

	public Ticket(int ticId, Event event, Seat seat, float ticPrice, int ticDiscount, boolean ticSold) {
		this.ticId = ticId;
		this.event = event;
		this.seat = seat;
		this.ticPrice = ticPrice;
		this.ticDiscount = ticDiscount;
		this.ticSold = ticSold;
	}

	public Ticket(int ticId, Event event, Seat seat, User user, float ticPrice, int ticDiscount, boolean ticSold) {
		this.ticId = ticId;
		this.event = event;
		this.seat = seat;
		this.user = user;
		this.ticPrice = ticPrice;
		this.ticDiscount = ticDiscount;
		this.ticSold = ticSold;
	}

	@Id
	@GenericGenerator(name="generator" , strategy="increment")
	@GeneratedValue(generator="generator")
	@Column(name = "TIC_ID", unique = true, nullable = false)
	public int getTicId() {
		return this.ticId;
	}

	public void setTicId(int ticId) {
		this.ticId = ticId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EVENT_ID", nullable = false)
	public Event getEvent() {
		return this.event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SEAT_ID", nullable = false)
	public Seat getSeat() {
		return this.seat;
	}

	public void setSeat(Seat seat) {
		this.seat = seat;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "TIC_PRICE", nullable = false, precision = 8)
	public float getTicPrice() {
		return this.ticPrice;
	}

	public void setTicPrice(float ticPrice) {
		this.ticPrice = ticPrice;
	}

	@Column(name = "TIC_DISCOUNT", nullable = false)
	public int getTicDiscount() {
		return this.ticDiscount;
	}

	public void setTicDiscount(int ticDiscount) {
		this.ticDiscount = ticDiscount;
	}

	@Column(name = "TIC_SOLD", nullable = false)
	public boolean isTicSold() {
		return this.ticSold;
	}

	public void setTicSold(boolean ticSold) {
		this.ticSold = ticSold;
	}

}