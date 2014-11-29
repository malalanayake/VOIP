package org.voip.model.report;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class SalesCommission {
	@Id @GeneratedValue
	private long id;
	private String customer;
	private String countryservice;
	private BigDecimal cost;
	private BigDecimal commission;
	
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public String getCountryservice() {
		return countryservice;
	}
	public void setCountryservice(String countryservice) {
		this.countryservice = countryservice;
	}
	public BigDecimal getCost() {
		return cost;
	}
	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}
	public BigDecimal getCommission() {
		return commission;
	}
	public void setCommission(BigDecimal commission) {
		this.commission = commission;
	}
	
	
}
