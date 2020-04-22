package com.grit.template.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class UserRole {
	@Id
	private String userRoleId;
	private String userName;
	private String userRoleDesc;
	public String getUserRoleId() {
		return userRoleId;
	}
	public void setUserRoleId(String userRoleId) {
		this.userRoleId = userRoleId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserRoleDesc() {
		return userRoleDesc;
	}
	public void setUserRoleDesc(String userRoleDesc) {
		this.userRoleDesc = userRoleDesc;
	}
	
	
	
}
