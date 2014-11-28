package org.voip.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.voip.model.Country;

/**
 * DAO for Country
 * 
 * @author malalanayake
 *
 */
@Repository
public interface CountryDAO extends CrudRepository<Country, Integer> {
	@Query(value="execute getCountryByName :countryName",nativeQuery = true)
	public Country getCountryByName(@Param("countryName") String countryName);
}
