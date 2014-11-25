package org.voip.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class CallRate {
	@Id @GeneratedValue
	private long id;
	
	@ManyToOne
	@JoinColumn(name="country_service_id")
	private CountryService countryService;
	
	@ManyToOne
	@JoinColumn(name="dest_country_code")
	private Country destCountry;
	
	private float peakRate;
	private float offPeakRate;
	private Date effectiveFrom;
	private Date effectiveTo;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public CountryService getCountryService() {
		return countryService;
	}
	public void setCountryService(CountryService countryService) {
		this.countryService = countryService;
	}
	public Country getDestCountry() {
		return destCountry;
	}
	public void setDestCountry(Country destCountry) {
		this.destCountry = destCountry;
	}
	public float getPeakRate() {
		return peakRate;
	}
	public void setPeakRate(float peakRate) {
		this.peakRate = peakRate;
	}
	public float getOffPeakRate() {
		return offPeakRate;
	}
	public void setOffPeakRate(float offPeakRate) {
		this.offPeakRate = offPeakRate;
	}
	public Date getEffectiveFrom() {
		return effectiveFrom;
	}
	public void setEffectiveFrom(Date effectiveFrom) {
		this.effectiveFrom = effectiveFrom;
	}
	public Date getEffectiveTo() {
		return effectiveTo;
	}
	public void setEffectiveTo(Date effectiveTo) {
		this.effectiveTo = effectiveTo;
	}
	
	
	

}
