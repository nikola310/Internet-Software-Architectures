package com.management.dto;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Zivko Stanisic
 *
 */
public class PerformanceDTO {
	

	private int perId;
	private char perType;
	@Size(min = 1, max = 30) @NotNull
	private String perGenre;
	@Size(min = 1, max = 50) @NotNull
	private String perDirector;
	@Min(1) @NotNull
	private int perDuration;
	@NotNull
	private Date perCreationDate;
	@Min(0) @NotNull
	private float perPrice;
	@Min(0) @NotNull
	private int perRank;
	private byte[] perPoster;
	@Size(min = 0, max = 200)
	private String perDescription;
	
	public int getPerId() {
		return perId;
	}
	public void setPerId(int perId) {
		this.perId = perId;
	}
	public char getPerType() {
		return perType;
	}
	public void setPerType(char perType) {
		this.perType = perType;
	}
	public String getPerGenre() {
		return perGenre;
	}
	public void setPerGenre(String perGenre) {
		this.perGenre = perGenre;
	}
	public String getPerDirector() {
		return perDirector;
	}
	public void setPerDirector(String perDirector) {
		this.perDirector = perDirector;
	}
	public int getPerDuration() {
		return perDuration;
	}
	public void setPerDuration(int perDuration) {
		this.perDuration = perDuration;
	}
	public Date getPerCreationDate() {
		return perCreationDate;
	}
	public void setPerCreationDate(Date perCreationDate) {
		this.perCreationDate = perCreationDate;
	}
	public float getPerPrice() {
		return perPrice;
	}
	public void setPerPrice(float perPrice) {
		this.perPrice = perPrice;
	}
	public byte[] getPerPoster() {
		return perPoster;
	}
	public void setPerPoster(byte[] perPoster) {
		this.perPoster = perPoster;
	}
	public String getPerDescription() {
		return perDescription;
	}
	public void setPerDescription(String perDescription) {
		this.perDescription = perDescription;
	}
	public int getPerRank() {
		return perRank;
	}
	public void setPerRank(int perRank) {
		this.perRank = perRank;
	}
}
