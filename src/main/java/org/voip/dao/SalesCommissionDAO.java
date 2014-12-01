package org.voip.dao;

import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.voip.model.report.SalesCommissionTotalReport;

public interface SalesCommissionDAO extends CrudRepository<SalesCommissionTotalReport, Integer>{
	@Query(value="execute getSalesReport :dateforMonth, :salesRepCode",nativeQuery = true)
	public SalesCommissionTotalReport getSalesReport(@Param("dateforMonth") Date dateforMonth,
			@Param("salesRepCode") long salesRepCode);

}
