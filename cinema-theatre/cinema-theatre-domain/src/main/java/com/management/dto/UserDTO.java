package com.management.dto;

import java.util.Date;

/**
 * @author Zivko Stanisic
 *
 */
public class UserDTO {
	
	private int userId;
	private String userEmail;
	private String userName;
	private String userSurname;
	private String userPassword;
	private String userCity;
	private boolean userActive;
	private Date userCreationDate;
	private char userAdmin;
	private Short userStateid;
	private Integer userPhone;
	private Integer userRank;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserSurname() {
		return userSurname;
	}
	public void setUserSurname(String userSurname) {
		this.userSurname = userSurname;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserCity() {
		return userCity;
	}
	public void setUserCity(String userCity) {
		this.userCity = userCity;
	}
	public boolean isUserActive() {
		return userActive;
	}
	public void setUserActive(boolean userActive) {
		this.userActive = userActive;
	}
	public Date getUserCreationDate() {
		return userCreationDate;
	}
	public void setUserCreationDate(Date userCreationDate) {
		this.userCreationDate = userCreationDate;
	}
	public char getUserAdmin() {
		return userAdmin;
	}
	public void setUserAdmin(char userAdmin) {
		this.userAdmin = userAdmin;
	}
	public Short getUserStateid() {
		return userStateid;
	}
	public void setUserStateid(Short userStateid) {
		this.userStateid = userStateid;
	}
	public Integer getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(Integer userPhone) {
		this.userPhone = userPhone;
	}
	public Integer getUserRank() {
		return userRank;
	}
	public void setUserRank(Integer userRank) {
		this.userRank = userRank;
	}
}
