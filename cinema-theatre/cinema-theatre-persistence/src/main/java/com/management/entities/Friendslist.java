package com.management.entities;
// Generated Apr 8, 2018 9:41:09 PM by Hibernate Tools 5.2.3.Final

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
 * Friendslist generated by hbm2java
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "friendslist", catalog = "isa")
public class Friendslist implements java.io.Serializable {

	private int friendsId;
	private User userByUseUserId2;
	private User userByUseUserId;
	private User userByUserId;
	private char friendsStatus;

	public Friendslist() {
	}

	public Friendslist(int friendsId, User userByUseUserId2, User userByUseUserId, User userByUserId,
			char friendsStatus) {
		this.friendsId = friendsId;
		this.userByUseUserId2 = userByUseUserId2;
		this.userByUseUserId = userByUseUserId;
		this.userByUserId = userByUserId;
		this.friendsStatus = friendsStatus;
	}

	@Id
	@GenericGenerator(name="generator" , strategy="increment")
	@GeneratedValue(generator="generator")
	@Column(name = "FRIENDS_ID", unique = true, nullable = false)
	public int getFriendsId() {
		return this.friendsId;
	}

	public void setFriendsId(int friendsId) {
		this.friendsId = friendsId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USE_USER_ID2", nullable = false)
	public User getUserByUseUserId2() {
		return this.userByUseUserId2;
	}

	public void setUserByUseUserId2(User userByUseUserId2) {
		this.userByUseUserId2 = userByUseUserId2;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USE_USER_ID", nullable = false)
	public User getUserByUseUserId() {
		return this.userByUseUserId;
	}

	public void setUserByUseUserId(User userByUseUserId) {
		this.userByUseUserId = userByUseUserId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", nullable = false)
	public User getUserByUserId() {
		return this.userByUserId;
	}

	public void setUserByUserId(User userByUserId) {
		this.userByUserId = userByUserId;
	}

	@Column(name = "FRIENDS_STATUS", nullable = false, length = 1)
	public char getFriendsStatus() {
		return this.friendsStatus;
	}

	public void setFriendsStatus(char friendsStatus) {
		this.friendsStatus = friendsStatus;
	}

}
