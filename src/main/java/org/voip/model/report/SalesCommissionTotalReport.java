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
public class SalesCommissionTotalReport {
	@Id @GeneratedValue
	private int id;
	
	private BigDecimal totalCommission;
	@OneToMany(fetch=FetchType.EAGER)
	@JoinColumn(name="report_id")
	private List<SalesCommission> salesCommissionList = new ArrayList<SalesCommission>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getTotalCommission() {
		return totalCommission;
	}

	public void setTotalCommission(BigDecimal totalCommission) {
		this.totalCommission = totalCommission;
	}

	public List<SalesCommission> getSalesCommissionList() {
		return salesCommissionList;
	}

	public void setSalesCommissionList(List<SalesCommission> salesCommissionList) {
		this.salesCommissionList = salesCommissionList;
	}

	
}
