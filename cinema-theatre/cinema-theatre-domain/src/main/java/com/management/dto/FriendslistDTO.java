package com.management.dto;

import com.management.entities.User;

/**
 * @author Zivko Stanisic
 *
 */
public class FriendslistDTO {
	
	private int friendsId;
	private User userByUseUserId2;
	private User userByUseUserId;
	private User userByUserId;
	private char friendsStatus;
	
	public int getFriendsId() {
		return friendsId;
	}
	public void setFriendsId(int friendsId) {
		this.friendsId = friendsId;
	}
	public User getUserByUseUserId2() {
		return userByUseUserId2;
	}
	public void setUserByUseUserId2(User userByUseUserId2) {
		this.userByUseUserId2 = userByUseUserId2;
	}
	public User getUserByUseUserId() {
		return userByUseUserId;
	}
	public void setUserByUseUserId(User userByUseUserId) {
		this.userByUseUserId = userByUseUserId;
	}
	public User getUserByUserId() {
		return userByUserId;
	}
	public void setUserByUserId(User userByUserId) {
		this.userByUserId = userByUserId;
	}
	public char getFriendsStatus() {
		return friendsStatus;
	}
	public void setFriendsStatus(char friendsStatus) {
		this.friendsStatus = friendsStatus;
	}
}
