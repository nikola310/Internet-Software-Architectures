/**
 * 
 */
package com.management.dto;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Nikola Stojanovic
 *
 */
public class CreatePropsDTO {

	@Size(min = 1, max = 50)
	@NotNull
	private String propsName;
	@NotNull
	private Date propsDeadline;
	@Min(0)
	@NotNull
	private float propsPrice;
	private byte[] propsImage;
	private String propsDesc;

	public String getPropsName() {
		return propsName;
	}

	public void setPropsName(String propsName) {
		this.propsName = propsName;
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
