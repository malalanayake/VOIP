package org.voip.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.voip.model.report.SalesCommission;

public interface SalesCommissionDAO extends CrudRepository<SalesCommission, Long>{
	@Query(value="execute getSalesReport :dateforMonth, :salesRepCode",nativeQuery = true)
	public List<SalesCommission> getSalesReport(@Param("dateforMonth") Date dateforMonth,
			@Param("salesRepCode") long salesRepCode);

}
