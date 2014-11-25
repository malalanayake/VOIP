package org.voip.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.voip.model.CountryService;

@Repository
public interface CountryServiceDAO extends CrudRepository<CountryService, Long> {

}
