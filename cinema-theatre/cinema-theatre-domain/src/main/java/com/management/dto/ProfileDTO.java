/**
 * 
 */
package com.management.dto;

import java.util.Date;

/**
 * @author Zivko Stanisic
 *
 */
public class ProfileDTO {

	private String userEmail;

	private String userName;

	private String userSurname;

	private String userCity;

	private char userAdmin;
	private String userStateid;
	private Integer userPhone;
	private Integer userRank;

	private Date userCreationDate;

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

	public String getUserCity() {
		return userCity;
	}

	public void setUserCity(String userCity) {
		this.userCity = userCity;
	}

	public char getUserAdmin() {
		return userAdmin;
	}

	public void setUserAdmin(char userAdmin) {
		this.userAdmin = userAdmin;
	}

	public String getUserStateid() {
		return userStateid;
	}

	public void setUserStateid(String userStateid) {
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

	public Date getUserCreationDate() {
		return userCreationDate;
	}

	public void setUserCreationDate(Date userCreationDate) {
		this.userCreationDate = userCreationDate;
	}
}
