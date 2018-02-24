package com.management.dto;

import java.util.Date;

import com.management.entities.Hall;
import com.management.entities.Performance;

/**
 * @author Zivko Stanisic
 *
 */
public class EventDTO {
	
	private int eventId;
	private Hall hall;
	private Performance performance;
	private Date eventDate;
	
	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	public Hall getHall() {
		return hall;
	}
	public void setHall(Hall hall) {
		this.hall = hall;
	}
	public Performance getPerformance() {
		return performance;
	}
	public void setPerformance(Performance performance) {
		this.performance = performance;
	}
	public Date getEventDate() {
		return eventDate;
	}
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
}
