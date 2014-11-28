package org.voip.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.voip.model.CallRate;

/**
 * DAO for Call Rate
 * 
 * @author DMalalanayake
 *
 */
@Repository
public interface CallRateDAO extends CrudRepository<CallRate, Long> {
	
	@Query(value="execute findLatestRate :countryServiceId",nativeQuery = true)
	public List<CallRate> findLatestRate(@Param("countryServiceId") long countryServiceId);
}
