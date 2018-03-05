package com.management.dto;

import java.util.Date;

/**
 * @author Zivko Stanisic
 *
 */
public class EventDTO {

	private int eventId;
	private Date eventDate;

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
}
