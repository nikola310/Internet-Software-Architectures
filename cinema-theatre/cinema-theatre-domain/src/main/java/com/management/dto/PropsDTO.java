package com.management.dto;

import java.util.Date;

import com.management.entities.FanZone;
import com.management.entities.User;

/**
 * @author Zivko Stanisic
 *
 */
public class PropsDTO {
	
	private int propsId;
	private FanZone fanZone;
	private User user;
	private String propsName;
	private boolean propsUsed;
	private char propsType;
	private Date propsDeadline;
	private float propsPrice;
	private byte[] propsImage;
	private String propsDesc;
	
	public int getPropsId() {
		return propsId;
	}
	public void setPropsId(int propsId) {
		this.propsId = propsId;
	}
	public FanZone getFanZone() {
		return fanZone;
	}
	public void setFanZone(FanZone fanZone) {
		this.fanZone = fanZone;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getPropsName() {
		return propsName;
	}
	public void setPropsName(String propsName) {
		this.propsName = propsName;
	}
	public boolean isPropsUsed() {
		return propsUsed;
	}
	public void setPropsUsed(boolean propsUsed) {
		this.propsUsed = propsUsed;
	}
	public char getPropsType() {
		return propsType;
	}
	public void setPropsType(char propsType) {
		this.propsType = propsType;
	}
	public Date getPropsDeadline() {
		return propsDeadline;
	}
	public void setPropsDeadline(Date propsDeadline) {
		this.propsDeadline = propsDeadline;
	}
	public float getPropsPrice() {
		return propsPrice;
	}
	public void setPropsPrice(float propsPrice) {
		this.propsPrice = propsPrice;
	}
	public byte[] getPropsImage() {
		return propsImage;
	}
	public void setPropsImage(byte[] propsImage) {
		this.propsImage = propsImage;
	}
	public String getPropsDesc() {
		return propsDesc;
	}
	public void setPropsDesc(String propsDesc) {
		this.propsDesc = propsDesc;
	}
}
