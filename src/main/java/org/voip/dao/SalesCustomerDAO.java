package org.voip.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.voip.model.SalesCustomer;

@Repository
public interface SalesCustomerDAO extends CrudRepository<SalesCustomer, Long>{

}
