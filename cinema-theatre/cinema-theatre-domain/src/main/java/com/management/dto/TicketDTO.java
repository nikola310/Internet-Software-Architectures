package com.management.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class TicketDTO {
	private int ticId;
	@Min(0)
	@NotNull
	private float ticPrice;
	@Min(0)
	@NotNull
	private int ticDiscount;
	@NotNull
	private boolean ticSold;
	public int getTicId() {
		return ticId;
	}
	public void setTicId(int ticId) {
		this.ticId = ticId;
	}
	public float getTicPrice() {
		return ticPrice;
	}
	public void setTicPrice(float ticPrice) {
		this.ticPrice = ticPrice;
	}
	public int getTicDiscount() {
		return ticDiscount;
	}
	public void setTicDiscount(int ticDiscount) {
		this.ticDiscount = ticDiscount;
	}
	public boolean isTicSold() {
		return ticSold;
	}
	public void setTicSold(boolean ticSold) {
		this.ticSold = ticSold;
	}
}
