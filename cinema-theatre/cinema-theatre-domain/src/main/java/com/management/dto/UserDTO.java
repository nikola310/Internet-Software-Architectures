package com.management.dto;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

/**
 * @author Zivko Stanisic
 *
 */
public class UserDTO {

	private int userId;
	@Email
	@NotNull
	private String userEmail;
	@Size(min = 1, max = 50)
	@NotNull
	private String userName;
	@Size(min = 1, max = 50)
	@NotNull
	private String userSurname;
	@Size(min = 1, max = 50)
	@NotNull
	private String userPassword;
	@Size(min = 1, max = 50)
	@NotNull
	private String userCity;
	@NotNull
	private char userAdmin;
	@Size(min = 3, max = 3)
	private String userStateid;
	@Min(0)
	private Integer userPhone;
	@Min(0)
	private Integer userRank;

	private Date userCreationDate;
	private Date userExpiration;
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

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

	public Date getUserExpiration() {
		return userExpiration;
	}

	public void setUserExpiration(Date userExpiration) {
		this.userExpiration = userExpiration;
	}
}
