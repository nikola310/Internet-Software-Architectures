package com.management.dto;

import com.management.entities.Event;
import com.management.entities.User;

/**
 * @author Zivko Stanisic
 *
 */
public class HistoryDTO {
	
	private int historyId;
	private Event event;
	private User user;
	private int historyPrice;
	
	public int getHistoryId() {
		return historyId;
	}
	public void setHistoryId(int historyId) {
		this.historyId = historyId;
	}
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getHistoryPrice() {
		return historyPrice;
	}
	public void setHistoryPrice(int historyPrice) {
		this.historyPrice = historyPrice;
	}
}
