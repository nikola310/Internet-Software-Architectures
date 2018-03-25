package com.management.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Zivko Stanisic
 *
 */
public class ActorDTO {

	private int acId;
	@Size(min = 1, max = 50) @NotNull
	private String acName;
	@Size(min = 1, max = 50) @NotNull
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
