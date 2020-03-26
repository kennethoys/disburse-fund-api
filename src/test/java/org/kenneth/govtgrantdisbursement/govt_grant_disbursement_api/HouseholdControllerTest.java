package org.kenneth.govtgrantdisbursement.govt_grant_disbursement_api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kenneth.govtgrantdisbursement.govt_grant_disbursement_api.controller.HouseholdController;
import org.kenneth.govtgrantdisbursement.govt_grant_disbursement_api.service.HouseholdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;;

@RunWith(SpringRunner.class)
@WebMvcTest(HouseholdController.class)
public class HouseholdControllerTest {
	
	@Autowired
    private MockMvc mvc;
	
	@MockBean
	HouseholdService householdService;
	
	@Test
	public void randomTest() throws Exception {
	}
}
