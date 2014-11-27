package org.voip.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.voip.model.SalesRep;

/**
 * DAO for Sales Rep
 * 
 * @author malalanayake
 *
 */
@Repository
public interface SalesRepDAO extends CrudRepository<SalesRep, Long> {
	@Query(value="execute getSalesRepByCode :salesRepCode",nativeQuery = true)
	public SalesRep getSalesRepByPK(@Param("salesRepCode") int salesRepCode);
}
