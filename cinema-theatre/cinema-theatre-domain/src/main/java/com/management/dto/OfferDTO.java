package com.management.dto;

/**
 * @author Nikola Stojanovic
 *
 */
public class OfferDTO {

	private int bidId;
	private float bidPrice;
	private String propsName;

	public OfferDTO() {
	}

	public OfferDTO(int bidId, float bidPrice, String propsName) {
		super();
		this.bidId = bidId;
		this.bidPrice = bidPrice;
		this.propsName = propsName;
	}

	public float getBidPrice() {
		return bidPrice;
	}

	public void setBidPrice(float bidPrice) {
		this.bidPrice = bidPrice;
	}

	public String getPropsName() {
		return propsName;
	}

	public void setPropsName(String propsName) {
		this.propsName = propsName;
	}

	public int getBidId() {
		return bidId;
	}

	public void setBidId(int bidId) {
		this.bidId = bidId;
	}
}
