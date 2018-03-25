package com.management.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author Zivko Stanisic
 *
 */
public class HistoryDTO {
	
	private int historyId;
	@Min(0) @NotNull
	private int historyPrice;
	
	public int getHistoryId() {
		return historyId;
	}
	public void setHistoryId(int historyId) {
		this.historyId = historyId;
	}
	
	public int getHistoryPrice() {
		return historyPrice;
	}
	public void setHistoryPrice(int historyPrice) {
		this.historyPrice = historyPrice;
	}
}
