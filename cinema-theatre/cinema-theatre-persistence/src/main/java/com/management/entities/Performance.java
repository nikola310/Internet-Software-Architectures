package com.management.entities;
// Generated Feb 22, 2018 10:07:38 PM by Hibernate Tools 5.2.3.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Zivko Stanisic
 * Performance generated by hbm2java
 */
@SuppressWarnings("serial")
public class Performance implements java.io.Serializable {

	private int perId;
	private char perType;
	private String perGenre;
	private String perDirector;
	private int perDuration;
	private Date perCreationDate;
	private float perPrice;
	private byte[] perPoster;
	private String perDescription;
	@SuppressWarnings("rawtypes")
	private Set events = new HashSet(0);

	public Performance() {
	}

	public Performance(int perId, char perType, String perGenre, String perDirector, int perDuration,
			Date perCreationDate, float perPrice) {
		this.perId = perId;
		this.perType = perType;
		this.perGenre = perGenre;
		this.perDirector = perDirector;
		this.perDuration = perDuration;
		this.perCreationDate = perCreationDate;
		this.perPrice = perPrice;
	}

	@SuppressWarnings("rawtypes")
	public Performance(int perId, char perType, String perGenre, String perDirector, int perDuration,
			Date perCreationDate, float perPrice, byte[] perPoster, String perDescription, Set events) {
		this.perId = perId;
		this.perType = perType;
		this.perGenre = perGenre;
		this.perDirector = perDirector;
		this.perDuration = perDuration;
		this.perCreationDate = perCreationDate;
		this.perPrice = perPrice;
		this.perPoster = perPoster;
		this.perDescription = perDescription;
		this.events = events;
	}

	public int getPerId() {
		return this.perId;
	}

	public void setPerId(int perId) {
		this.perId = perId;
	}

	public char getPerType() {
		return this.perType;
	}

	public void setPerType(char perType) {
		this.perType = perType;
	}

	public String getPerGenre() {
		return this.perGenre;
	}

	public void setPerGenre(String perGenre) {
		this.perGenre = perGenre;
	}

	public String getPerDirector() {
		return this.perDirector;
	}

	public void setPerDirector(String perDirector) {
		this.perDirector = perDirector;
	}

	public int getPerDuration() {
		return this.perDuration;
	}

	public void setPerDuration(int perDuration) {
		this.perDuration = perDuration;
	}

	public Date getPerCreationDate() {
		return this.perCreationDate;
	}

	public void setPerCreationDate(Date perCreationDate) {
		this.perCreationDate = perCreationDate;
	}

	public float getPerPrice() {
		return this.perPrice;
	}

	public void setPerPrice(float perPrice) {
		this.perPrice = perPrice;
	}

	public byte[] getPerPoster() {
		return this.perPoster;
	}

	public void setPerPoster(byte[] perPoster) {
		this.perPoster = perPoster;
	}

	public String getPerDescription() {
		return this.perDescription;
	}

	public void setPerDescription(String perDescription) {
		this.perDescription = perDescription;
	}

	@SuppressWarnings("rawtypes")
	public Set getEvents() {
		return this.events;
	}

	@SuppressWarnings("rawtypes")
	public void setEvents(Set events) {
		this.events = events;
	}

}
