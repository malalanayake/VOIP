package org.voip.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.voip.dao.ServiceDAO;
import org.voip.model.Service;

@org.springframework.stereotype.Service
public class ServiceService {
	@Autowired
	private ServiceDAO serviceDao;
	
	public Service getService(int serviceId){
		return serviceDao.findOne((long) serviceId);
	}
}
