package org.voip.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class SalesRep {
	@Id @GeneratedValue
	private long id;
	@OneToMany(mappedBy="salesRep")
	private List<SalesCustomer> salesCustomer = new ArrayList();

}
