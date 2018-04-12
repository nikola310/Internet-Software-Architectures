package com.management.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 
 * @author Nikola Stojanovic
 *
 */
public class BidDTO {

	private int bidId;
	@NotNull
	@Min(1)
	private float bidPrice;
	private Boolean bidAccepted;
	@NotNull
	private int userId;
	@NotNull
	private int propsId;

	public int getBidId() {
		return bidId;
	}

	public void setBidId(int bidId) {
		this.bidId = bidId;
	}

	public float getBidPrice() {
		return bidPrice;
	}

	public void setBidPrice(float bidPrice) {
		this.bidPrice = bidPrice;
	}

	public Boolean isBidAccepted() {
		return bidAccepted;
	}

	public void setBidAccepted(Boolean bidAccepted) {
		this.bidAccepted = bidAccepted;
	}

	public int isUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getPropsId() {
		return propsId;
	}

	public void setPropsId(int propsId) {
		this.propsId = propsId;
	}

}
