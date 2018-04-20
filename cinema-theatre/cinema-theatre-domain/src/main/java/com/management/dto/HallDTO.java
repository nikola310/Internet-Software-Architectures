package com.management.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Zivko Stanisic
 *
 */
public class HallDTO {

	private int hallId;
	@NotNull
	private int ctId;
	@Size(min = 1, max = 50)
	@NotNull
	private String hallName;
	@Min(0)
	@NotNull
	private int len;

	public int getCtId() {
		return ctId;
	}

	public void setCtId(int ctId) {
		this.ctId = ctId;
	}

	public int getLen() {
		return len;
	}

	public void setLen(int seats) {
		this.len = seats;
	}

	public int getHallId() {
		return hallId;
	}

	public void setHallId(int hallId) {
		this.hallId = hallId;
	}

	public String getHallName() {
		return hallName;
	}

	public void setHallName(String hallName) {
		this.hallName = hallName;
	}

}
