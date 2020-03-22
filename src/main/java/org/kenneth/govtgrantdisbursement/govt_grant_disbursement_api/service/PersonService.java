package org.kenneth.govtgrantdisbursement.govt_grant_disbursement_api.service;

import java.util.List;

import org.kenneth.govtgrantdisbursement.govt_grant_disbursement_api.model.Person;
import org.kenneth.govtgrantdisbursement.govt_grant_disbursement_api.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService implements IPersonService {
	
	@Autowired
	private PersonRepository repository;
	
	public List<Person> listAll() {
		return (List<Person>) repository.findAll();
	}
	
	public void save(Person person) {
		repository.save(person);
	}
	
	public Person get(Long id) {
		return repository.findById(id).get();
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
}
