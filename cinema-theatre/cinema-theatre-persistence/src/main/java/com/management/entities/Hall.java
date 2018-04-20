package com.management.entities;
// Generated Apr 18, 2018 12:50:36 AM by Hibernate Tools 5.2.8.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * Hall generated by hbm2java
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "hall", catalog = "isa")
public class Hall implements java.io.Serializable {

	private int hallId;
	private CinemaTheatre cinemaTheatre;
	private String hallName;
	private Set<Event> events = new HashSet<Event>(0);
	private Set<Segment> segments = new HashSet<Segment>(0);
	private Set<Seat> seats = new HashSet<Seat>(0);

	public Hall() {
	}

	public Hall(int hallId, CinemaTheatre cinemaTheatre, String hallName) {
		this.hallId = hallId;
		this.cinemaTheatre = cinemaTheatre;
		this.hallName = hallName;
	}

	public Hall(int hallId, CinemaTheatre cinemaTheatre, String hallName, Set<Event> events, Set<Segment> segments) {
		this.hallId = hallId;
		this.cinemaTheatre = cinemaTheatre;
		this.hallName = hallName;
		this.events = events;
		this.segments = segments;
	}

	@Id
	@GenericGenerator(name="generator" , strategy="increment")
	@GeneratedValue(generator="generator")
	@Column(name = "HALL_ID", unique = true, nullable = false)
	public int getHallId() {
		return this.hallId;
	}

	public void setHallId(int hallId) {
		this.hallId = hallId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CT_ID", nullable = false)
	public CinemaTheatre getCinemaTheatre() {
		return this.cinemaTheatre;
	}

	public void setCinemaTheatre(CinemaTheatre cinemaTheatre) {
		this.cinemaTheatre = cinemaTheatre;
	}

	@Column(name = "HALL_NAME", nullable = false, length = 50)
	public String getHallName() {
		return this.hallName;
	}

	public void setHallName(String hallName) {
		this.hallName = hallName;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "hall")
	public Set<Event> getEvents() {
		return this.events;
	}

	public void setEvents(Set<Event> events) {
		this.events = events;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "hall")
	public Set<Segment> getSegments() {
		return this.segments;
	}

	public void setSegments(Set<Segment> segments) {
		this.segments = segments;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "hall")
	public Set<Seat> getSeats() {
		return this.seats;
	}

	public void setSeats(Set<Seat> seats) {
		this.seats = seats;
	}

}
