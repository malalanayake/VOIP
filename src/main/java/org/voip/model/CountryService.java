package org.voip.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class CountryService {
	@Id @GeneratedValue
	private long id;
	
	@ManyToOne
	@JoinColumn(name="country_code")
	private Country country;
	
	@ManyToOne
	@JoinColumn(name="service_id")
	private Service service;
	
	@OneToMany(mappedBy="countryService")
	private List<CallRate> callRates = new ArrayList();
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	public Service getService() {
		return service;
	}
	public void setService(Service service) {
		this.service = service;
	}
	
	
	

}
