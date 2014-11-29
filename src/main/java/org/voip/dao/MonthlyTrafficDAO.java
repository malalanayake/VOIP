package org.voip.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.voip.model.report.MonthlyTraffic;

public interface MonthlyTrafficDAO extends CrudRepository<MonthlyTraffic, Long>{
	@Query(value="execute getMonthlyTraffic :dateforMonth",nativeQuery = true)
	public List<MonthlyTraffic> getMonthlyTraffic(@Param("dateforMonth") Date dateforMonth );
}
