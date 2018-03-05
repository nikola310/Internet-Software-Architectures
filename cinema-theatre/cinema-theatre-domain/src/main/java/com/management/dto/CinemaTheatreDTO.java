package com.management.dto;

/**
 * @author Zivko Stanisic
 *
 */
public class CinemaTheatreDTO {
	
	private int ctId;
	private String ctName;
	private String ctStateid;
	private Integer ctPhone;
	private String ctAdress;
	private String ctDescription;
	
	public String getCtStateid() {
		return ctStateid;
	}
	public void setCtStateid(String ctStateid) {
		this.ctStateid = ctStateid;
	}
	public Integer getCtPhone() {
		return ctPhone;
	}
	public void setCtPhone(Integer ctPhone) {
		this.ctPhone = ctPhone;
	}
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
