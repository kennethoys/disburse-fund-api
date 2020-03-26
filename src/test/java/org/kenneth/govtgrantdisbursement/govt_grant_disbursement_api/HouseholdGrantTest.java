package org.kenneth.govtgrantdisbursement.govt_grant_disbursement_api;

import org.junit.Test;
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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=App.class)
@AutoConfigureMockMvc
public class HouseholdGrantTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private HouseholdService householdService;
	
	@Test
	public void a_init() throws Exception {
		assert householdService.listAllHousehold().size() == 0 : "Table households should be deleted or empty before run";
		assert householdService.listAllPeople().size() == 0 : "Table people should be deleted or empty before run";
		
		// seb ygstg
		Household household1 = new Household("HDB");
		Person person2 = new Person("Terrence Tan", "Male", "Single", "Student", 0, LocalDate.of(2014, 4, 9), null, household1);
		household1.getPeople().add(person2);
		Person person3 = new Person("Raymond Tan", "Male", "Single", "Employed", 43000, LocalDate.of(1989, 3, 17), null, household1);
		household1.getPeople().add(person3);
		householdService.save(household1);
		
		Household household2 = new Household("HDB");
		Person person4 = new Person("Sylvia Lim", "Female", "Single", "Student", 0, LocalDate.of(2013, 5, 17), null, household2);
		household2.getPeople().add(person4);
		Person person5 = new Person("Grace Lim", "Female", "Single", "Employed", 43000, LocalDate.of(1986, 4, 20), null, household2);
		household2.getPeople().add(person5);
		householdService.save(household2);
		
		// fts
		Household household3 = new Household("Condominium");
		Person person6 = new Person("Terrence Lim", "Male", "Married", "Employed", 80000, LocalDate.of(1990, 8, 12), null, household3);
		household3.getPeople().add(person6);
		Person person7 = new Person("Cryster Lim", "Female", "Married", "Employed", 80000, LocalDate.of(1993, 6, 15), null, household3);
		household3.getPeople().add(person7);
		person6.setSpouse(person7);
		person7.setSpouse(person6);
		Person person8 = new Person("Louis Lim", "Female", "Single", "Student", 0, LocalDate.of(2010, 1, 1), null, household3);
		household3.getPeople().add(person8);
		householdService.save(household3);
		
		// eb ygstg
		Household household4 = new Household("HDB");
		Person person9 = new Person("Tan Kok Seng", "Male", "Single", "Unemployed", 0, LocalDate.of(1964, 1, 15), null, household4);
		household4.getPeople().add(person9);
		Person person10 = new Person("Tan Tok Xi", "Male", "Single", "Employed", 30000, LocalDate.of(1986, 4, 27), null, household4);
		household4.getPeople().add(person10);
		householdService.save(household4);
		
		// bsg ygstg
		Household household5 = new Household("HDB");
		Person person11 = new Person("Baby Ong", "Male", "Single", "Unemployed", 0, LocalDate.of(2019, 8, 23), null, household5);
		household5.getPeople().add(person11);
		householdService.save(household5);
	}
	
	@Test
	public void sebTest() throws Exception {
		this.mvc.perform(MockMvcRequestBuilders.get("/households/grants/1")
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$").value(not(empty())))
			.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void ftsTest() throws Exception {
		this.mvc.perform(MockMvcRequestBuilders.get("/households/grants/2")
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$").value(not(empty())))
			.andDo(MockMvcResultHandlers.print());
	}
	
	
	@Test
	public void ebTest() throws Exception {
		this.mvc.perform(MockMvcRequestBuilders.get("/households/grants/3")
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$").value(not(empty())))
			.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void bsgTest() throws Exception {
		this.mvc.perform(MockMvcRequestBuilders.get("/households/grants/4")
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$").value(not(empty())))
			.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void ygstgTest() throws Exception {
		this.mvc.perform(MockMvcRequestBuilders.get("/households/grants/5")
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$").value(not(empty())))
			.andDo(MockMvcResultHandlers.print());
	}
}
