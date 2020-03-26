package org.kenneth.govtgrantdisbursement.govt_grant_disbursement_api.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.kenneth.govtgrantdisbursement.govt_grant_disbursement_api.model.Household;
import org.kenneth.govtgrantdisbursement.govt_grant_disbursement_api.model.Person;
import org.kenneth.govtgrantdisbursement.govt_grant_disbursement_api.service.HouseholdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HouseholdController {
	
	@Autowired
	private HouseholdService householdService;
	
	@GetMapping("/households")
	public List<Household> listHouseholds() {
		return householdService.listAllHousehold();
	}
	
	@GetMapping("/households/{id}")
	public ResponseEntity<Household> getHousehold(@PathVariable Long id) {
		try {
			Household household = householdService.getHousehold(id);
			return new ResponseEntity<Household> (household, HttpStatus.OK);
			
		} catch (NoSuchElementException e) {
			return new ResponseEntity<Household> (HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/households/grants/{option}")
	public List<Household> getGrants(@PathVariable int option) {
		return householdService.getGrantDisbursement(option);
	}
	
	@PostMapping("/households")
	public void addHousehold(@RequestBody Household household) {
		householdService.save(household);
	}
	
	@PostMapping("/households/{householdId}")
	public void addPersonToHousehold(@RequestBody Person person, @PathVariable Long householdId) {
		householdService.addPersonToHousehold(householdId, person);
	}
	
	@DeleteMapping("/households/{id}")
	public void deleteHousehold(@PathVariable Long id) {
		householdService.deleteHousehold(id);
	}
	
	@DeleteMapping("/people/{id}")
	public void removePersonFromHousehold(@PathVariable Long id) {
		householdService.removePersonFromHousehold(id);
	}
}
