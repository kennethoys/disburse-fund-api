package org.kenneth.govtgrantdisbursement.govt_grant_disbursement_api.service;

import java.util.List;

import org.kenneth.govtgrantdisbursement.govt_grant_disbursement_api.model.Household;
import org.kenneth.govtgrantdisbursement.govt_grant_disbursement_api.model.Person;
import org.kenneth.govtgrantdisbursement.govt_grant_disbursement_api.repository.HouseholdRepository;
import org.kenneth.govtgrantdisbursement.govt_grant_disbursement_api.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HouseholdService implements IHouseholdService {
	
	@Autowired
	private HouseholdRepository householdRepository;
	
	@Autowired
	private PersonRepository personRepository;
	
	public List<Household> listAll() {
		return (List<Household>) householdRepository.findAll();
	}
	
	public void save(Household household) {
		householdRepository.save(household);
	}
	
	public Household get(Long id) {
		return householdRepository.findById(id).get();
	}
	
	public void delete(Long id) {
		householdRepository.deleteById(id);
	}
	
	public void addPersonToHousehold(Long householdId, Person person) {
		Household household = get(householdId);
		person.setHousehold(household);
		personRepository.save(person);
		//household.getPeople().add(person);
		//save(household);
	}
	
	public void removePersonFromHousehold(Long personId) {
		Household household = personRepository.findById(personId).get().getHousehold();
		household.getPeople().removeIf(people -> people.getId().equals(personId));
		save(household);
	}
}
