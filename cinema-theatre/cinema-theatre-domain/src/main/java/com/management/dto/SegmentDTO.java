package com.management.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class SegmentDTO {
	private int segId;
	@Size(min = 1, max = 50)
	@NotNull
	private String segName;
	@Min(0)
	private int segPosition;
	@Min(0)
	private int segNumberOfSeats;
	public int getSegId() {
		return segId;
	}
	public void setSegId(int segId) {
		this.segId = segId;
	}
	public String getSegName() {
		return segName;
	}
	public void setSegName(String segName) {
		this.segName = segName;
	}
	public int getSegPosition() {
		return segPosition;
	}
	public void setSegPosition(int segPosition) {
		this.segPosition = segPosition;
	}
	public int getSegNumberOfSeats() {
		return segNumberOfSeats;
	}
	public void setSegNumberOfSeats(int segNumberOfSeats) {
		this.segNumberOfSeats = segNumberOfSeats;
	}
}
