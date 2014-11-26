package org.voip.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.voip.model.CountryService;

/**
 * DAO for Country service
 * 
 * @author malalanayake
 *
 */
@Repository
public interface CountryServiceDAO extends CrudRepository<CountryService, Long> {
	@Query(value="execute getCountryService :countryCode, :serviceName",nativeQuery = true)
	public CountryService getCountryServiceByPK(@Param("countryCode") int counntryCode,@Param("serviceName") String serviceName);
}
