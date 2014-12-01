package org.voip.dao;

import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.voip.model.report.CustomerMonthlyTotalReport;

public interface CustomerMonthlyDAO extends CrudRepository<CustomerMonthlyTotalReport, Integer> {
	
	@Query(value="execute getMonthlyReport :dateforMonth,:customerId",nativeQuery = true)
	public CustomerMonthlyTotalReport getReportByMonthACustomer(@Param("dateforMonth") Date dateforMonth,@Param("customerId") long customerId );
}
