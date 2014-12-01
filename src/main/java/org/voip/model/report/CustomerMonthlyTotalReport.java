package org.voip.model.report;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class CustomerMonthlyTotalReport {
	@Id @GeneratedValue
	private int id;
	@OneToMany(fetch=FetchType.EAGER)
	@JoinColumn(name="report_id")
	private List<CustomerMonthly> allCustomerMonthly=new ArrayList<CustomerMonthly>();
	
	private BigDecimal totalCost;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<CustomerMonthly> getAllCustomerMonthly() {
		return allCustomerMonthly;
	}

	public void setAllCustomerMonthly(List<CustomerMonthly> allCustomerMonthly) {
		this.allCustomerMonthly = allCustomerMonthly;
	}

	public BigDecimal getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
	}
	
	
	
}
