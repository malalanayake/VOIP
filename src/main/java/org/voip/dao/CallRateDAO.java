package org.voip.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.voip.model.CallRate;

@Repository
public interface CallRateDAO extends CrudRepository<CallRate, Long>{

}
