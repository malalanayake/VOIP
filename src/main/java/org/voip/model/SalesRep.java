package org.voip.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class SalesRep {
	@Id @GeneratedValue
	private long id;
	
	private long code;
	@OneToMany(mappedBy="salesRep",fetch=FetchType.EAGER)
	private List<SalesCustomer> salesCustomer = new ArrayList<SalesCustomer>();
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public void addSalesCustomer(SalesCustomer salesCustomer){
		this.salesCustomer.add(salesCustomer);
	}
	public List<SalesCustomer> getSalesCustomer() {
		return salesCustomer;
	}
	public void setSalesCustomer(List<SalesCustomer> salesCustomer) {
		this.salesCustomer = salesCustomer;
	}
	public long getCode() {
		return code;
	}
	public void setCode(long code) {
		this.code = code;
	}
	
	
}
