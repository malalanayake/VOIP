package org.voip.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.voip.model.CallRate;
import org.voip.model.Country;
import org.voip.model.CountryService;
import org.voip.model.Service;

/**
 * DAO for Country service
 * 
 * @author malalanayake
 *
 */
@Repository
public interface CountryServiceDAO extends CrudRepository<CountryService, Long> {
	@Query(value = "execute getCountryService :countryCode, :serviceName", nativeQuery = true)
	public CountryService getCountryServiceByPK(
			@Param("countryCode") int countryCode,
			@Param("serviceName") String serviceName);

	@Query(value = "execute getCountryServiceByCName :countryName, :serviceName", nativeQuery = true)
	public CountryService getCountryServiceByPK(
			@Param("countryName") String countryName,
			@Param("serviceName") String serviceName);

	@Query("Select a from CountryService a where country=:country and service=:service")
	public CountryService findByCountryAndService(
			@Param("country") Country country, @Param("service") Service service);
}
