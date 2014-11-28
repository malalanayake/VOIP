package org.voip.report.model;

import java.util.Date;

public class CustomerReport {
	private int toCode;
	private long toNumber;
	private int duration;
	private Date callDateTime;
	private double rate;
	private double totalAmount;
	public int getToCode() {
		return toCode;
	}
	public void setToCode(int toCode) {
		this.toCode = toCode;
	}
	public long getToNumber() {
		return toNumber;
	}
	public void setToNumber(long toNumber) {
		this.toNumber = toNumber;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public Date getCallDateTime() {
		return callDateTime;
	}
	public void setCallDateTime(Date callDateTime) {
		this.callDateTime = callDateTime;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
}
