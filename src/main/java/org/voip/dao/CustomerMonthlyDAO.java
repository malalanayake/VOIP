package org.voip.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.voip.model.report.CustomerMonthly;

public interface CustomerMonthlyDAO extends CrudRepository<CustomerMonthly, Long> {
	
	@Query(value="execute getMonthlyReport :dateforMonth,:customerId",nativeQuery = true)
	public List<CustomerMonthly> getReportByMonthACustomer(@Param("dateforMonth") Date dateforMonth,@Param("customerId") long customerId );
}
