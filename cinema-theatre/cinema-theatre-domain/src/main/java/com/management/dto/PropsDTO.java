package com.management.dto;

import java.util.Date;

import javax.validation.constraints.Min;
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
	@NotNull
	private Date propsDeadline;
	@Min(0)
	@NotNull
	private float propsPrice;
	@NotNull
	private int userId;
	private byte[] propsImage;
	private String propsDesc;
	private Boolean propsApproved;

	public PropsDTO(){
		
	}
	
	public PropsDTO(String propsName, Date propsDeadline,
			float propsPrice, int userId, byte[] propsImage, String propsDesc) {
		this.propsName = propsName;
		this.propsDeadline = propsDeadline;
		this.propsPrice = propsPrice;
		this.userId = userId;
		if (!(propsImage == null))
			this.propsImage = propsImage;
		if (!(propsDesc == null))
			this.propsDesc = propsDesc;
	}

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

	public Boolean isPropsApproved() {
		return propsApproved;
	}

	public void setPropsApproved(Boolean propsApproved) {
		this.propsApproved = propsApproved;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
}
