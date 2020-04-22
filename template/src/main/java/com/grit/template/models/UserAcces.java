package com.grit.template.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserAcces {

	@Id
	private String userAccesId;
	private String userRoleId;
	private String categori;
	private String userAccesDesc;
	public String getUserAccesId() {
		return userAccesId;
	}
	public void setUserAccesId(String userAccesId) {
		this.userAccesId = userAccesId;
	}
	public String getUserRoleId() {
		return userRoleId;
	}
	public void setUserRoleId(String userRoleId) {
		this.userRoleId = userRoleId;
	}
	public String getCategori() {
		return categori;
	}
	public void setCategori(String categori) {
		this.categori = categori;
	}
	public String getUserAccesDesc() {
		return userAccesDesc;
	}
	public void setUserAccesDesc(String userAccesDesc) {
		this.userAccesDesc = userAccesDesc;
	}
}
