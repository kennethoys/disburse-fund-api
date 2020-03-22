package org.kenneth.govtgrantdisbursement.govt_grant_disbursement_api.service;

import java.util.List;

import org.kenneth.govtgrantdisbursement.govt_grant_disbursement_api.model.Household;

public interface IHouseholdService {
	
	List<Household> listAll();
}
