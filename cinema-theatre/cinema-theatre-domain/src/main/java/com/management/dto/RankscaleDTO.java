package com.management.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author Nikola Stojanovic
 *
 */
public class RankscaleDTO {

	private int scaleId;
	@NotNull
	private int userId;
	@NotNull
	@Min(1)
	private int scaleBronze;
	@NotNull
	@Min(1)
	private int scaleSilver;
	@NotNull
	@Min(1)
	private int scaleGold;
	@NotNull
	private boolean scaleActive;

	public RankscaleDTO(int scaleBronze, int scaleSilver, int scaleGold) {
		this.scaleBronze = scaleBronze;
		this.scaleSilver = scaleSilver;
		this.scaleGold = scaleGold;
	}

	public RankscaleDTO() {
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getScaleBronze() {
		return scaleBronze;
	}

	public void setScaleBronze(int scaleBronze) {
		this.scaleBronze = scaleBronze;
	}

	public int getScaleSilver() {
		return scaleSilver;
	}

	public void setScaleSilver(int scaleSilver) {
		this.scaleSilver = scaleSilver;
	}

	public int getScaleGold() {
		return scaleGold;
	}

	public void setScaleGold(int scaleGold) {
		this.scaleGold = scaleGold;
	}

	public boolean isScaleActive() {
		return scaleActive;
	}

	public void setScaleActive(boolean scaleActive) {
		this.scaleActive = scaleActive;
	}

	public int getScaleId() {
		return scaleId;
	}

	public void setScaleId(int propsId) {
		this.scaleId = propsId;
	}
}
