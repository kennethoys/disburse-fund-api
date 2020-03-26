package org.kenneth.govtgrantdisbursement.govt_grant_disbursement_api.model;

import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="people")
public class Person {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="gender")
	private String gender;
	
	@Column(name="marital_status")
	private String maritalStatus;
	
	@Column(name="occupation_type")
	private String occupationType;
	
	@Column(name="annual_income")
	private int annualIncome;
	
	@Column(name="birth_date")
	private LocalDate birthDate;
	
	@OneToOne(cascade=CascadeType.ALL)
	private Person spouse;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="household_id")
	private Household household;
	
	public Person() {
	}
	public Person(String name, String gender, String maritalStatus, String occupationType, int annualIncome,
			LocalDate birthDate, Person spouse, Household household) {
		this.name = name;
		this.gender = gender;
		this.maritalStatus = maritalStatus;
		this.occupationType = occupationType;
		this.annualIncome = annualIncome;
		this.birthDate = birthDate;
		this.spouse = spouse;
		this.household = household;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public String getOccupationType() {
		return occupationType;
	}
	public void setOccupationType(String occupationType) {
		this.occupationType = occupationType;
	}
	public int getAnnualIncome() {
		return annualIncome;
	}
	public void setAnnualIncome(int annualIncome) {
		this.annualIncome = annualIncome;
	}
	public LocalDate getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	public Person getSpouse() {
		return spouse;
	}
	public void setSpouse(Person spouse) {
		this.spouse = spouse;
	}
	public Household getHousehold() {
		return household;
	}
	public void setHousehold(Household household) {
		this.household = household;
	}
}
