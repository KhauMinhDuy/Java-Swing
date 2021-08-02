package com.khauminhduy.event;

import java.util.EventObject;

public class FormEvent extends EventObject {

	private static final long serialVersionUID = 1L;

	private String name;
	private String occupation;
	private String age;
	private String employee;
	private String taxId;
	private boolean usCitizen;
	private String gender;

	public FormEvent(Object source) {
		super(source);
	}

	public FormEvent(Object source, String name, String occupation, String age, String employee,
			String taxId, boolean usCitizen, String gender) {
		super(source);
		this.name = name;
		this.occupation = occupation;
		this.age = age;
		this.employee = employee;
		this.taxId = taxId;
		this.usCitizen = usCitizen;
		this.gender = gender;
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

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

}
