package org.voip.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class CallDetail {
	@Id @GeneratedValue
	private long id;
	
	@ManyToOne
	private Country fromCountry;
	
	@ManyToOne
	private Customer fromCustomer;
	
	@ManyToOne
	private Country toCountry;
	
	private long toTel;
	private long duration;
	
	@Temporal(TemporalType.DATE)
	private Date callDate;
	private int callTime;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Country getFromCountry() {
		return fromCountry;
	}
	public void setFromCountry(Country fromCountry) {
		this.fromCountry = fromCountry;
	}
	public Customer getFromCustomer() {
		return fromCustomer;
	}
	public void setFromCustomer(Customer fromCustomer) {
		this.fromCustomer = fromCustomer;
	}
	public Country getToCountry() {
		return toCountry;
	}
	public void setToCountry(Country toCountry) {
		this.toCountry = toCountry;
	}
	public long getToTel() {
		return toTel;
	}
	public void setToTel(long toTel) {
		this.toTel = toTel;
	}
	public long getDuration() {
		return duration;
	}
	public void setDuration(long duration) {
		this.duration = duration;
	}
	public Date getCallDate() {
		return callDate;
	}
	public void setCallDate(Date callDate) {
		this.callDate = callDate;
	}
	public int getCallTime() {
		return callTime;
	}
	public void setCallTime(int callTime) {
		this.callTime = callTime;
	}
	
	
	
	
	

}
