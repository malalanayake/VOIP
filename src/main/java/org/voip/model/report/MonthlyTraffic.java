package org.voip.model.report;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class MonthlyTraffic {
	@Id 
	private long id;
	private String fromCountry;
	private String serviceName;
	private String toCountry;
	private long minutesOfCalls;
	
	public String getFromCountry() {
		return fromCountry;
	}
	public void setFromCountry(String fromCountry) {
		this.fromCountry = fromCountry;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getToCountry() {
		return toCountry;
	}
	public void setToCountry(String toCountry) {
		this.toCountry = toCountry;
	}
	public long getMinutesOfCalls() {
		return minutesOfCalls;
	}
	public void setMinutesOfCalls(long minutesOfCalls) {
		this.minutesOfCalls = minutesOfCalls;
	}
	
	
}
