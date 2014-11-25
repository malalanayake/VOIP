package org.voip.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.voip.model.CallDetail;

/**
 * DAO for Call Details
 * 
 * @author malalanayake
 *
 */
@Repository
public interface CallDetailDAO extends CrudRepository<CallDetail, Long> {

}
