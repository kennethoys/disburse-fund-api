package org.kenneth.govtgrantdisbursement.govt_grant_disbursement_api.service;

import java.util.List;

import org.kenneth.govtgrantdisbursement.govt_grant_disbursement_api.model.Person;

public interface IPersonService {
	
	List<Person> listAll();
	void save(Person person);
	Person get(Long id);
	void delete(Long id);
}
