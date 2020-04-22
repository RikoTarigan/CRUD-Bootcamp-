package com.grit.template.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {
	@Id
	private String userName;
	private String userPassword;
	private String userFirstName;
	private String userLastName;
	private String userPhone;
	private boolean userIsActive;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserFirstName() {
		return userFirstName;
	}
	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}
	public String getUserLastName() {
		return userLastName;
	}
	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public boolean isUserIsActive() {
		return userIsActive;
	}
	public void setUserIsActive(boolean userIsActive) {
		this.userIsActive = userIsActive;
	}
	

}
