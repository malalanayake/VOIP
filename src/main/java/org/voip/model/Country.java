package org.voip.model;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Country {
	@Id
	private int code;
	private String name;
	private int peakTime;
	private int offPeakTime;
	
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPeakTime() {
		return peakTime;
	}
	public void setPeakTime(int peakTime) {
		this.peakTime = peakTime;
	}
	public int getOffPeakTime() {
		return offPeakTime;
	}
	public void setOffPeakTime(int offPeakTime) {
		this.offPeakTime = offPeakTime;
	}
	

}
