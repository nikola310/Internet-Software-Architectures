package com.management.dto;

/**
 * @author Zivko Stanisic
 *
 */
public class CinemaTheatreDTO {
	
	private int ctId;
	private String ctName;
	private String ctPhone;
	private String ctAdress;
	private String ctDescription;
	
	public int getCtId() {
		return ctId;
	}
	public void setCtId(int ctId) {
		this.ctId = ctId;
	}
	public String getCtName() {
		return ctName;
	}
	public void setCtName(String ctName) {
		this.ctName = ctName;
	}
	public String getCtPhone() {
		return ctPhone;
	}
	public void setCtPhone(String ctPhone) {
		this.ctPhone = ctPhone;
	}
	public String getCtAdress() {
		return ctAdress;
	}
	public void setCtAdress(String ctAdress) {
		this.ctAdress = ctAdress;
	}
	public String getCtDescription() {
		return ctDescription;
	}
	public void setCtDescription(String ctDescription) {
		this.ctDescription = ctDescription;
	}
}
