package com.management.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Zivko Stanisic
 *
 */
public class PropsDTO {

	private int propsId;
	@Size(min = 1, max = 50)
	@NotNull
	private String propsName;
	@NotNull
	private boolean propsUsed;
	private char propsType;
	@NotNull
	private Date propsDeadline;
	@NotNull
	private float propsPrice;
	
	private byte[] propsImage;
	private String propsDesc;

	public int getPropsId() {
		return propsId;
	}

	public void setPropsId(int propsId) {
		this.propsId = propsId;
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
