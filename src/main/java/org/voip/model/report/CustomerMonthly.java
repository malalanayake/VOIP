package org.voip.model.report;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class CustomerMonthly {
	@Id @GeneratedValue
	private long id;
	@Temporal(TemporalType.DATE)
	private Date date;
	private int time;
	private long duration;
	private String country;
	private long phoneno;
	private BigDecimal cost;
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getTime() {
		String s = String.valueOf(time);
		StringBuilder timeStampBuilder=new StringBuilder();
		int length = s.length();
		if(length<=2){
			timeStampBuilder.append("00:");
			if(s.length()==1)
				timeStampBuilder.append("0");
			timeStampBuilder.append(s+":00");
		}
		else if(length>2&&length<=4){
			String min= s.substring(length-2, length);
			String hr = s.substring(0,length-2);
			if(hr.length()==1)
				timeStampBuilder.append("0");
			timeStampBuilder.append(hr);
			timeStampBuilder.append(":"+min+":00");
		}
		else if(length>4&&length<=6){
			String sec= s.substring(length-2, length);
			String min = s.substring(length-4,length-2);
			String hr = s.substring(0,length-4);
			if(hr.length()==1)
				timeStampBuilder.append("0");
			timeStampBuilder.append(hr);
			timeStampBuilder.append(":"+min+":"+sec);
		}		
		return timeStampBuilder.toString();
	}
	public void setTime(int time) {
		this.time =time;
	}
	public String getDuration() {
		int tempDuration=(int) duration;
		StringBuilder timeDurationBuilder = new StringBuilder();
		int hr = (int) (tempDuration/3600);
		if(hr/10==0)
			timeDurationBuilder.append("0");
		timeDurationBuilder.append(hr);
		timeDurationBuilder.append(":");
		tempDuration = tempDuration % 3600;
		int min =  (tempDuration /60);
		if(min/10==0)
			timeDurationBuilder.append("0");
		tempDuration = tempDuration % 60;
		timeDurationBuilder.append(min);
		timeDurationBuilder.append(":");
		int sec = tempDuration;
		if(sec/10==0)
			timeDurationBuilder.append("0");
		timeDurationBuilder.append(sec);
		return timeDurationBuilder.toString();
	}
	
	public void setDuration(long duration) {
		this.duration=duration;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public long getPhoneno() {
		return phoneno;
	}
	public void setPhoneno(long phoneno) {
		this.phoneno = phoneno;
	}
	public BigDecimal getCost() {
		return cost;
	}
	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}
	
}
