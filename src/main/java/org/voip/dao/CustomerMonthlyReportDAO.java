package org.voip.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.voip.model.report.CustomerMonthlyReport;

public interface CustomerMonthlyReportDAO extends CrudRepository<CustomerMonthlyReport, Long> {
	
	@Query(value="execute getMonthlyReport :dateforMonth,:customerId",nativeQuery = true)
	public List<CustomerMonthlyReport> getReportByMonthACustomer(@Param("dateforMonth") Date dateforMonth,@Param("customerId") long customerId );
}
