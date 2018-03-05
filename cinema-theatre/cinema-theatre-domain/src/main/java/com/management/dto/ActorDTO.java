package com.management.dto;

/**
 * @author Zivko Stanisic
 *
 */
public class ActorDTO {

	private int acId;
	private String acName;
	private String acSurname;
	
	public int getAcId() {
		return acId;
	}
	public void setAcId(int acId) {
		this.acId = acId;
	}
	public String getAcName() {
		return acName;
	}
	public void setAcName(String acName) {
		this.acName = acName;
	}
	public String getAcSurname() {
		return acSurname;
	}
	public void setAcSurname(String acSurname) {
		this.acSurname = acSurname;
	}
}
