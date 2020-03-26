package org.kenneth.govtgrantdisbursement.govt_grant_disbursement_api;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.kenneth.govtgrantdisbursement.govt_grant_disbursement_api.model.Household;
import org.kenneth.govtgrantdisbursement.govt_grant_disbursement_api.model.Person;
import org.kenneth.govtgrantdisbursement.govt_grant_disbursement_api.service.HouseholdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.NoSuchElementException;


@RunWith(SpringRunner.class)
@SpringBootTest(classes=App.class)
@AutoConfigureMockMvc
public class HouseholdIntegrationTest {
	
	@Autowired
    private MockMvc mvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private HouseholdService householdService;
	
	private static long householdId = 0;
	private static long personId = 0;
	
	@BeforeAll
	public void checkCondition() throws Exception {
		assert householdService.listAllHousehold().size() == 0 : "Table households should be deleted or empty before run";
		assert householdService.listAllPeople().size() == 0 : "Table people should be deleted or empty before run";
	}
	
	@Test
	public void createHouseholdTest() throws Exception {
		String housingType = "HDB";
		householdId++;
		Household household = new Household(housingType);
		
		this.mvc.perform(MockMvcRequestBuilders.post("/households")
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(household)))
			.andExpect(status().isOk());
		
		assert housingType.equals(householdService.getHousehold(householdId).getHousingType()) : "housingType is not the same";
	}
	
	@Test
	public void showHouseholdTest() throws Exception {
		Household household = new Household("Condominium");
		householdId++;
		Person person = new Person("Andrew Yeo", "M", "Single", "Employed", 40000, LocalDate.of(1992, 7, 12), null, household);
		personId++;
		household.getPeople().add(person);
		householdService.save(household);
				
		this.mvc.perform(MockMvcRequestBuilders.get("/households/" + householdId)
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.people[0].name", is("Andrew Yeo")));
	}
	
	@Test
	public void listHouseholdsTest() throws Exception {
		this.mvc.perform(MockMvcRequestBuilders.get("/households")
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$").value(not(empty())));
	}
	
	
	@Test
	public void deleteHouseholdTest() throws Exception {
		Household household = new Household("Landed");
		householdId++;
		Person person = new Person("Terrence Tan", "M", "Single", "Employed", 43000, LocalDate.of(1989, 4, 9), null, household);
		personId++;
		household.getPeople().add(person);
		householdService.save(household);
		
		this.mvc.perform(MockMvcRequestBuilders.delete("/households/" + householdId)
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());
		
		try {
			householdService.getHousehold(householdId);
			assert false : "Household not deleted";
		} catch(NoSuchElementException e) {}
		
		try {
			householdService.getPerson(personId);
			assert false : "Person not deleted";
		} catch(NoSuchElementException e) {}
	}
	
	
	@Test
	public void addPersonToHouseholdTest() throws Exception {
		Household household = new Household("HDB");
		householdId++;
		householdService.save(household);
		
		Person person = new Person("Terrence Tan", "M", "Single", "Employed", 43000, LocalDate.of(1989, 4, 9), null, household);
		personId++;
		
		this.mvc.perform(MockMvcRequestBuilders.post("/households/" + householdId)
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(person)))
			.andExpect(status().isOk());
		
		assert householdService.getHousehold(householdId).getPeople().size() > 0 : "Person was not added to Household";
	}
	
	@Test
	public void removePersonFromHouseholdTest() throws Exception {
		Household household = new Household("Landed");
		householdId++;
		Person person = new Person("Louis Low", "M", "Single", "Employed", 27000, LocalDate.of(1993, 8, 17), null, household);
		personId++;
		household.getPeople().add(person);
		householdService.save(household);
		
		this.mvc.perform(MockMvcRequestBuilders.delete("/people/" + personId)
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());
		
		assert householdService.getHousehold(householdId).getPeople().size() == 0 : "Person was not removed from Household";
	}
}
