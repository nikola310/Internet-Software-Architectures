package com.management.entities;
// Generated Mar 14, 2018 9:09:33 PM by Hibernate Tools 5.2.3.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author Zivko Stanisic
 * User generated by hbm2java
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "user", catalog = "isa")
public class User implements java.io.Serializable {

	private int userId;
	private String userEmail;
	private String userName;
	private String userSurname;
	private String userPassword;
	private String userCity;
	private boolean userActive;
	private Date userCreationDate;
	private char userAdmin;
	private String userStateid;
	private Integer userPhone;
	private Integer userRank;
	private Set<History> histories = new HashSet<History>(0);
	private Set<Friendslist> friendslistsForUseUserId2 = new HashSet<Friendslist>(0);
	private Set<Props> propses = new HashSet<Props>(0);
	private Set<Seat> seats = new HashSet<Seat>(0);
	private Set<Friendslist> friendslistsForUseUserId = new HashSet<Friendslist>(0);
	private Set<Friendslist> friendslistsForUserId = new HashSet<Friendslist>(0);

	public User() {
	}

	public User(int userId, String userEmail, String userName, String userSurname, String userPassword, String userCity,
			boolean userActive, Date userCreationDate, char userAdmin) {
		this.userId = userId;
		this.userEmail = userEmail;
		this.userName = userName;
		this.userSurname = userSurname;
		this.userPassword = userPassword;
		this.userCity = userCity;
		this.userActive = userActive;
		this.userCreationDate = userCreationDate;
		this.userAdmin = userAdmin;
	}

	public User(int userId, String userEmail, String userName, String userSurname, String userPassword, String userCity,
			boolean userActive, Date userCreationDate, char userAdmin, String userStateid, Integer userPhone,
			Integer userRank, Set<History> histories, Set<Friendslist> friendslistsForUseUserId2, Set<Props> propses,
			Set<Seat> seats, Set<Friendslist> friendslistsForUseUserId, Set<Friendslist> friendslistsForUserId) {
		this.userId = userId;
		this.userEmail = userEmail;
		this.userName = userName;
		this.userSurname = userSurname;
		this.userPassword = userPassword;
		this.userCity = userCity;
		this.userActive = userActive;
		this.userCreationDate = userCreationDate;
		this.userAdmin = userAdmin;
		this.userStateid = userStateid;
		this.userPhone = userPhone;
		this.userRank = userRank;
		this.histories = histories;
		this.friendslistsForUseUserId2 = friendslistsForUseUserId2;
		this.propses = propses;
		this.seats = seats;
		this.friendslistsForUseUserId = friendslistsForUseUserId;
		this.friendslistsForUserId = friendslistsForUserId;
	}

	@Id
	@GenericGenerator(name="generator" , strategy="increment")
	@GeneratedValue(generator="generator")
	@Column(name = "USER_ID", unique = true, nullable = false)
	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Column(name = "USER_EMAIL", nullable = false, length = 50)
	public String getUserEmail() {
		return this.userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	@Column(name = "USER_NAME", nullable = false, length = 50)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "USER_SURNAME", nullable = false, length = 50)
	public String getUserSurname() {
		return this.userSurname;
	}

	public void setUserSurname(String userSurname) {
		this.userSurname = userSurname;
	}

	@Column(name = "USER_PASSWORD", nullable = false, length = 50)
	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	@Column(name = "USER_CITY", nullable = false, length = 50)
	public String getUserCity() {
		return this.userCity;
	}

	public void setUserCity(String userCity) {
		this.userCity = userCity;
	}

	@Column(name = "USER_ACTIVE", nullable = false)
	public boolean isUserActive() {
		return this.userActive;
	}

	public void setUserActive(boolean userActive) {
		this.userActive = userActive;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "USER_CREATION_DATE", nullable = false, length = 19)
	public Date getUserCreationDate() {
		return this.userCreationDate;
	}

	public void setUserCreationDate(Date userCreationDate) {
		this.userCreationDate = userCreationDate;
	}

	@Column(name = "USER_ADMIN", nullable = false, length = 1)
	public char getUserAdmin() {
		return this.userAdmin;
	}

	public void setUserAdmin(char userAdmin) {
		this.userAdmin = userAdmin;
	}

	@Column(name = "USER_STATEID", length = 3)
	public String getUserStateid() {
		return this.userStateid;
	}

	public void setUserStateid(String userStateid) {
		this.userStateid = userStateid;
	}

	@Column(name = "USER_PHONE")
	public Integer getUserPhone() {
		return this.userPhone;
	}

	public void setUserPhone(Integer userPhone) {
		this.userPhone = userPhone;
	}

	@Column(name = "USER_RANK")
	public Integer getUserRank() {
		return this.userRank;
	}

	public void setUserRank(Integer userRank) {
		this.userRank = userRank;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<History> getHistories() {
		return this.histories;
	}

	public void setHistories(Set<History> histories) {
		this.histories = histories;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userByUseUserId2")
	public Set<Friendslist> getFriendslistsForUseUserId2() {
		return this.friendslistsForUseUserId2;
	}

	public void setFriendslistsForUseUserId2(Set<Friendslist> friendslistsForUseUserId2) {
		this.friendslistsForUseUserId2 = friendslistsForUseUserId2;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Props> getPropses() {
		return this.propses;
	}

	public void setPropses(Set<Props> propses) {
		this.propses = propses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Seat> getSeats() {
		return this.seats;
	}

	public void setSeats(Set<Seat> seats) {
		this.seats = seats;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userByUseUserId")
	public Set<Friendslist> getFriendslistsForUseUserId() {
		return this.friendslistsForUseUserId;
	}

	public void setFriendslistsForUseUserId(Set<Friendslist> friendslistsForUseUserId) {
		this.friendslistsForUseUserId = friendslistsForUseUserId;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userByUserId")
	public Set<Friendslist> getFriendslistsForUserId() {
		return this.friendslistsForUserId;
	}

	public void setFriendslistsForUserId(Set<Friendslist> friendslistsForUserId) {
		this.friendslistsForUserId = friendslistsForUserId;
	}

}
