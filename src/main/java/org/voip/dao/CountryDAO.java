package org.voip.dao;

import org.springframework.data.repository.CrudRepository;
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

}
