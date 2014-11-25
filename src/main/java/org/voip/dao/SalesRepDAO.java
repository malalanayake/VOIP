package org.voip.dao;

import org.springframework.data.repository.CrudRepository;
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

}
