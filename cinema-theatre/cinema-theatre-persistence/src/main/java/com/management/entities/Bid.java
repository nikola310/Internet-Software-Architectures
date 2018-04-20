package com.management.entities;

// Generated Apr 20, 2018 9:30:21 AM by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * Bid generated by hbm2java
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "bid", catalog = "isa")
public class Bid implements java.io.Serializable {

	private int bidId;
	private Props props;
	private User user;
	private float bidPrice;
	private Boolean bidAccepted;

	public Bid() {
	}

	public Bid(int bidId, Props props, User user, float bidPrice) {
		this.bidId = bidId;
		this.props = props;
		this.user = user;
		this.bidPrice = bidPrice;
	}

	public Bid(int bidId, Props props, User user, float bidPrice,
			Boolean bidAccepted) {
		this.bidId = bidId;
		this.props = props;
		this.user = user;
		this.bidPrice = bidPrice;
		this.bidAccepted = bidAccepted;
	}

	@Id
	@GenericGenerator(name="generator" , strategy="increment")
	@GeneratedValue(generator="generator")
	@Column(name = "BID_ID", unique = true, nullable = false)
	public int getBidId() {
		return this.bidId;
	}

	public void setBidId(int bidId) {
		this.bidId = bidId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PROPS_ID", nullable = false)
	public Props getProps() {
		return this.props;
	}

	public void setProps(Props props) {
		this.props = props;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", nullable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "BID_PRICE", nullable = false, precision = 8)
	public float getBidPrice() {
		return this.bidPrice;
	}

	public void setBidPrice(float bidPrice) {
		this.bidPrice = bidPrice;
	}

	@Column(name = "BID_ACCEPTED")
	public Boolean getBidAccepted() {
		return this.bidAccepted;
	}

	public void setBidAccepted(Boolean bidAccepted) {
		this.bidAccepted = bidAccepted;
	}

}
