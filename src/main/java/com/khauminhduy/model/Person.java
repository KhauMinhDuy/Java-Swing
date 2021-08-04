package com.khauminhduy.model;

public class Person {

	private static int count = 0;
	private int id;
	private String name;
	private String occupation;
	private AgeCategory ageCategory;
	private String employee;
	private String taxId;
	private boolean usCitizen;
	private Gender gender;

	public Person() {
		super();
	}

	public Person(String name, String occupation, AgeCategory ageCategory, String employee, String taxId,
			boolean usCitizen, Gender gender) {
		super();
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

	public String getEmployee() {
		return employee;
	}

	public void setEmployee(String employee) {
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

}
