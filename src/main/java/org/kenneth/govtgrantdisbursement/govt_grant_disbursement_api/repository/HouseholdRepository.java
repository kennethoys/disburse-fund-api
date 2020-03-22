package org.kenneth.govtgrantdisbursement.govt_grant_disbursement_api.repository;

import org.kenneth.govtgrantdisbursement.govt_grant_disbursement_api.model.Household;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseholdRepository extends JpaRepository<Household, Long> {
}
