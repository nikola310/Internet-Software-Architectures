package com.management.entities;

// Generated Apr 2, 2018 4:06:14 PM by Hibernate Tools 4.3.1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * Props generated by hbm2java
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "props", catalog = "isa")
public class Props implements java.io.Serializable {

	private int propsId;
	private User user;
	private String propsName;
	private boolean propsUsed;
	private Date propsDeadline;
	private float propsPrice;
	private byte[] propsImage;
	private String propsDesc;
	private boolean propsApproved;
	private Set<Bid> bids = new HashSet<Bid>(0);

	public Props() {
	}

	public Props(int propsId, User user, String propsName, boolean propsUsed,
			Date propsDeadline, float propsPrice) {
		this.propsId = propsId;
		this.user = user;
		this.propsName = propsName;
		this.propsUsed = propsUsed;
		this.propsDeadline = propsDeadline;
		this.propsPrice = propsPrice;
	}

	public Props(int propsId, User user, String propsName, boolean propsUsed,
			Date propsDeadline, float propsPrice, byte[] propsImage,
			String propsDesc, boolean propsApproved, Set<Bid> bids) {
		this.propsId = propsId;
		this.user = user;
		this.propsName = propsName;
		this.propsUsed = propsUsed;
		this.propsDeadline = propsDeadline;
		this.propsPrice = propsPrice;
		this.propsImage = propsImage;
		this.propsDesc = propsDesc;
		this.propsApproved = propsApproved;
		this.bids = bids;
	}

	@Id
	@GenericGenerator(name="generator" , strategy="increment")
	@GeneratedValue(generator="generator")
	@Column(name = "PROPS_ID", unique = true, nullable = false)
	public int getPropsId() {
		return this.propsId;
	}

	public void setPropsId(int propsId) {
		this.propsId = propsId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", nullable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "PROPS_NAME", nullable = false, length = 50)
	public String getPropsName() {
		return this.propsName;
	}

	public void setPropsName(String propsName) {
		this.propsName = propsName;
	}

	@Column(name = "PROPS_USED", nullable = false)
	public boolean isPropsUsed() {
		return this.propsUsed;
	}

	public void setPropsUsed(boolean propsUsed) {
		this.propsUsed = propsUsed;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "PROPS_DEADLINE", nullable = false, length = 19)
	public Date getPropsDeadline() {
		return this.propsDeadline;
	}

	public void setPropsDeadline(Date propsDeadline) {
		this.propsDeadline = propsDeadline;
	}

	@Column(name = "PROPS_PRICE", nullable = false, precision = 8)
	public float getPropsPrice() {
		return this.propsPrice;
	}

	public void setPropsPrice(float propsPrice) {
		this.propsPrice = propsPrice;
	}

	@Column(name = "PROPS_IMAGE")
	public byte[] getPropsImage() {
		return this.propsImage;
	}

	public void setPropsImage(byte[] propsImage) {
		this.propsImage = propsImage;
	}

	@Column(name = "PROPS_DESC", length = 120)
	public String getPropsDesc() {
		return this.propsDesc;
	}

	public void setPropsDesc(String propsDesc) {
		this.propsDesc = propsDesc;
	}

	@Column(name = "PROPS_APPROVED")
	public boolean getPropsApproved() {
		return this.propsApproved;
	}

	public void setPropsApproved(boolean propsApproved) {
		this.propsApproved = propsApproved;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "props")
	public Set<Bid> getBids() {
		return this.bids;
	}

	public void setBids(Set<Bid> bids) {
		this.bids = bids;
	}

}
