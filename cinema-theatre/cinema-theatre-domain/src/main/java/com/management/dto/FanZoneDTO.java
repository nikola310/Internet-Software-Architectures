package com.management.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Zivko Stanisic
 *
 */
public class FanZoneDTO {
	
	private int fanZoneId;
	@Size(min = 1, max = 50) @NotNull
	private String fanZoneName;
	
	public int getFanZoneId() {
		return fanZoneId;
	}
	public void setFanZoneId(int fanZoneId) {
		this.fanZoneId = fanZoneId;
	}
	public String getFanZoneName() {
		return fanZoneName;
	}
	public void setFanZoneName(String fanZoneName) {
		this.fanZoneName = fanZoneName;
	}
}
