package org.kenneth.govtgrantdisbursement.govt_grant_disbursement_api.service;

import java.util.List;

import org.kenneth.govtgrantdisbursement.govt_grant_disbursement_api.model.Household;
import org.kenneth.govtgrantdisbursement.govt_grant_disbursement_api.repository.HouseholdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HouseholdService implements IHouseholdService {
	
	@Autowired
	private HouseholdRepository repository;
	
	public List<Household> listAll() {
		return (List<Household>) repository.findAll();
	}
}
