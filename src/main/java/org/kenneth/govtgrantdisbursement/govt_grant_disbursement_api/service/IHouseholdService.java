package org.kenneth.govtgrantdisbursement.govt_grant_disbursement_api.service;

import java.util.List;

import org.kenneth.govtgrantdisbursement.govt_grant_disbursement_api.model.Household;
import org.kenneth.govtgrantdisbursement.govt_grant_disbursement_api.model.Person;

public interface IHouseholdService {
	
	List<Household> listAll();
	void save(Household household);
	Household get(Long id);
	void delete(Long id);
	void addPersonToHousehold(Long householdId, Person person);
	void removePersonFromHousehold(Long personId);
}
