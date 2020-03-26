package org.kenneth.govtgrantdisbursement.govt_grant_disbursement_api.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.kenneth.govtgrantdisbursement.govt_grant_disbursement_api.model.Household;
import org.kenneth.govtgrantdisbursement.govt_grant_disbursement_api.model.Person;
import org.kenneth.govtgrantdisbursement.govt_grant_disbursement_api.repository.HouseholdRepository;
import org.kenneth.govtgrantdisbursement.govt_grant_disbursement_api.repository.PersonRepository;
import org.kenneth.govtgrantdisbursement.govt_grant_disbursement_api.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HouseholdService {
	
	@Autowired
	private HouseholdRepository householdRepository;
	
	@Autowired
	private PersonRepository personRepository;
	
	public List<Household> listAllHousehold() {
		return (List<Household>) householdRepository.findAll();
	}
	
	public List<Person> listAllPeople() {
		return (List<Person>) personRepository.findAll();
	}
	
	public void save(Household household) {
		householdRepository.save(household);
	}
	
	public void save(Person person) {
		personRepository.save(person);
	}
	
	public Household getHousehold(Long id) {
		return householdRepository.findById(id).get();
	}
	
	public Person getPerson(Long id) {
		return personRepository.findById(id).get();
	}
	
	public void deleteHousehold(Long id) {
		householdRepository.deleteById(id);
	}
	
	public void deletePerson(Long id) {
		personRepository.deleteById(id);
	}
	
	public void addPersonToHousehold(Long householdId, Person person) {
		Household household = getHousehold(householdId);
		person.setHousehold(household);
		personRepository.save(person);
	}
	
	public void removePersonFromHousehold(Long personId) {
		Household household = personRepository.findById(personId).get().getHousehold();
		household.getPeople().removeIf(people -> people.getId().equals(personId));
		save(household);
	}
	
	public List<Household> getGrantDisbursement(int option) {
		switch(option) {
			case 1:
				return getSEB();
			case 2:
				return getFTS();
			case 3:
				return getEB();
			case 4:
				return getBSG();
			case 5:
				return getYGSTG();
			default:
				return new ArrayList<Household> ();
		}
	}
	
	// Student Encouragement Bonus
	private List<Household> getSEB() {
		return listAllHousehold().stream().filter(household -> {
			Set<Person> people = household.getPeople();
			int totalIncome = 0;
			boolean lessThan16 = false;
			
			for(Person person: people) {
				totalIncome += person.getAnnualIncome();
				
				if(DateUtil.calculateAge(person.getBirthDate(), LocalDate.now()) < 16)
					lessThan16 = true;
				
				else
					people.remove(person);
			}
			
			return totalIncome < 150000 && lessThan16;
		}).collect(Collectors.toList());
	}
	
	// Family Togetherness Scheme
	private List<Household> getFTS() {
		return listAllHousehold().stream().filter(household -> {
			Set<Person> people = household.getPeople();
			boolean hasCouple = false;
			boolean hasChildrenBelow18 = false;
			
			for(Person person: people) {
				
				if(DateUtil.calculateAge(person.getBirthDate(), LocalDate.now()) < 18) {
					hasChildrenBelow18 = true;
				}
				else {
					
					if(person.getSpouse().getHousehold().equals(person.getHousehold()))
						hasCouple = true;
					
					else
						people.remove(person);
				}
			}
			
			return hasCouple && hasChildrenBelow18;
		}).collect(Collectors.toList());
	}
	
	// Elder Bonus
	private List<Household> getEB() {
		return listAllHousehold().stream().filter(household -> {
			Set<Person> people = household.getPeople();
			boolean hasElderAbove50 = false;
			
			for(Person person: people) {
				
				if(DateUtil.calculateAge(person.getBirthDate(), LocalDate.now()) > 50)
					hasElderAbove50 = true;
					
				else
					people.remove(person);
			}
			
			return hasElderAbove50;
		}).collect(Collectors.toList());
	}
	
	// Baby Sunshine Grant
	private List<Household> getBSG() {
		return listAllHousehold().stream().filter(household -> {
			Set<Person> people = household.getPeople();
			boolean hasChildrenYoungerThan5 = false;
			
			for(Person person: people) {
				
				if(DateUtil.calculateAge(person.getBirthDate(), LocalDate.now()) < 5)
					hasChildrenYoungerThan5 = true;
					
				else
					people.remove(person);
			}
			
			return hasChildrenYoungerThan5;
		}).collect(Collectors.toList());
	}
	
	// YOLO GST Grant
	private List<Household> getYGSTG() {
		return listAllHousehold().stream().filter(household -> {
			Set<Person> people = household.getPeople();
			int totalIncome = 0;
			
			for(Person person: people) {
				totalIncome += person.getAnnualIncome();
			}
			
			return totalIncome < 100000;
		}).collect(Collectors.toList());
	}
}
