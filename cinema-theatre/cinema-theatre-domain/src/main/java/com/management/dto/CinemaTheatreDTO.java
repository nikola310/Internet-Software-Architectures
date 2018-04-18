package com.management.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Zivko Stanisic
 *
 */
public class CinemaTheatreDTO {
	
	private int ctId;
	@Size(min = 1, max = 50) @NotNull
	private String ctName;
	@Size(min = 3, max = 3) @NotNull
	private String ctStateid;
	@Min(0) @NotNull
	private Integer ctPhone;
	@Size(min = 1, max = 50) @NotNull
	private String ctAdress;
	@Size(min = 1, max = 200)
	private String ctDescription;
	@NotNull
	private char ctType;
	
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
	public char getCtType() {
		return ctType;
	}
	public void setCtType(char ctType) {
		this.ctType = ctType;
	}
	
}
