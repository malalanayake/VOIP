package org.voip.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.voip.model.Service;

/**
 * DAO for Service
 * 
 * @author malalanayake
 *
 */
@Repository
public interface ServiceDAO extends CrudRepository<Service, Long> {

}
