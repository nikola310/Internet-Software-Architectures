package com.management.dto;

import java.util.Date;

/**
 * @author Zivko Stanisic
 *
 */
public class PerformanceDTO {
	
	private int perId;
	private char perType;
	private String perGenre;
	private String perDirector;
	private int perDuration;
	private Date perCreationDate;
	private float perPrice;
	private byte[] perPoster;
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
}
