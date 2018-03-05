package com.management.dto;

/**
 * @author Zivko Stanisic
 *
 */
public class FriendslistDTO {
	
	private int friendsId;
	private char friendsStatus;
	
	public int getFriendsId() {
		return friendsId;
	}
	public void setFriendsId(int friendsId) {
		this.friendsId = friendsId;
	}

	public char getFriendsStatus() {
		return friendsStatus;
	}
	public void setFriendsStatus(char friendsStatus) {
		this.friendsStatus = friendsStatus;
	}
}
