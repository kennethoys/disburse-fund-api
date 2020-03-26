package org.kenneth.govtgrantdisbursement.govt_grant_disbursement_api.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="households")
public class Household {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="housing_type")
	private String housingType;
	
	@JsonManagedReference
	@OneToMany(
			mappedBy="household",
			cascade=CascadeType.ALL,
			orphanRemoval=true
	)
	private Set<Person> people;
	
	public Household() {
	}
	public Household(String housingType) {
		this.housingType = housingType;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getHousingType() {
		return housingType;
	}
	public void setHousingType(String housingType) {
		this.housingType = housingType;
	}
	public Set<Person> getPeople() {
		return people;
	}
	public void setPeople(Set<Person> people) {
		this.people = people;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == this)
			return true;
		
		if(obj == null || obj.getClass() != this.getClass())
			return false;
		
		Household household = (Household) obj;
		
		return id.equals(household.getId()) && housingType.equals(household.getHousingType()) && people.equals(household.getPeople());
	}
}
