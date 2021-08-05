package com.khauminhduy.model;

import java.io.Serializable;

public class Person implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private static int count = 0;
	private int id;
	private String name;
	private String occupation;
	private AgeCategory ageCategory;
	private Employment employee;
	private String taxId;
	private boolean usCitizen;
	private Gender gender;

	public Person() {
	}

	public Person(String name, String occupation, AgeCategory ageCategory, Employment employee, String taxId,
			boolean usCitizen, Gender gender) {
		this.id = count++;
		this.name = name;
		this.occupation = occupation;
		this.ageCategory = ageCategory;
		this.employee = employee;
		this.taxId = taxId;
		this.usCitizen = usCitizen;
		this.gender = gender;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public AgeCategory getAgeCategory() {
		return ageCategory;
	}

	public void setAgeCategory(AgeCategory ageCategory) {
		this.ageCategory = ageCategory;
	}

	public Employment getEmployee() {
		return employee;
	}

	public void setEmployee(Employment employee) {
		this.employee = employee;
	}

	public String getTaxId() {
		return taxId;
	}

	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}

	public boolean isUsCitizen() {
		return usCitizen;
	}

	public void setUsCitizen(boolean usCitizen) {
		this.usCitizen = usCitizen;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", occupation=" + occupation + ", ageCategory=" + ageCategory
				+ ", employee=" + employee + ", taxId=" + taxId + ", usCitizen=" + usCitizen + ", gender=" + gender
				+ "]";
	}

}
