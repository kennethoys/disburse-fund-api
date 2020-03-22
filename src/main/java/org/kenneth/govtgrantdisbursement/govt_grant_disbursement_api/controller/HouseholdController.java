package org.kenneth.govtgrantdisbursement.govt_grant_disbursement_api.controller;

import java.util.List;

import org.kenneth.govtgrantdisbursement.govt_grant_disbursement_api.model.Household;
import org.kenneth.govtgrantdisbursement.govt_grant_disbursement_api.service.IHouseholdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HouseholdController {
	
	@Autowired
	private IHouseholdService householdService;
	
	@GetMapping("/listHouseholds")
	public List<Household> listHouseholds() {
		return householdService.listAll();
	}
}
