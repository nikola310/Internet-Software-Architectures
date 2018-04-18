package com.management.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

/**
 * @author Zivko Stanisic
 *
 */
public class LoginDTO {

	@Email
	@NotNull
	private String email;
	@NotNull
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
