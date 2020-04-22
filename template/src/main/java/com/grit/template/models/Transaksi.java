package com.grit.template.models;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Transaksi {
	@Id
	private String trCode;
	private LocalDate trDate;
	private String custName;
	private double totalAmout;
	public String getTrCode() {
		return trCode;
	}
	public void setTrCode(String trCode) {
		this.trCode = trCode;
	}
	public LocalDate getTrDate() {
		return trDate;
	}
	public void setTrDate(LocalDate localDate) {
		this.trDate = localDate;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public double getTotalAmout() {
		return totalAmout;
	}
	public void setTotalAmout(double totalAmout) {
		this.totalAmout = totalAmout;
	}
}
