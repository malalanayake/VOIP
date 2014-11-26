package org.voip.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class SalesRep {
	@Id 
	private long id;
	@OneToMany(mappedBy="salesRep")
	private List<SalesCustomer> salesCustomer = new ArrayList();
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public List<SalesCustomer> getSalesCustomer() {
		return salesCustomer;
	}
	public void setSalesCustomer(List<SalesCustomer> salesCustomer) {
		this.salesCustomer = salesCustomer;
	}
	
	
}
