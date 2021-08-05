package com.khauminhduy.controller;

import java.util.List;

import com.khauminhduy.db.Database;
import com.khauminhduy.event.FormEvent;
import com.khauminhduy.model.AgeCategory;
import com.khauminhduy.model.Employment;
import com.khauminhduy.model.Gender;
import com.khauminhduy.model.Person;

public class Controller {

	private Database database;
	
	public Controller() {
		database = new Database();
	}

	public void addPerson(FormEvent event) {
		String name = event.getName();
		String occupation = event.getOccupation();
		String ageCat = event.getAgeCategory();
		String employee = event.getEmployee();
		String taxId = event.getTaxId();
		boolean usCitizen = event.isUsCitizen();
		String genderstr = event.getGender();
		
		AgeCategory ageCategory = null;
		switch(ageCat) {
		case "Under 18":
			ageCategory = ageCategory.CHILD;
			break;
		case "18 to 69":
			ageCategory = ageCategory.ADULT;
			break;
		case "69 or over":
			ageCategory = ageCategory.SENIOR;
			break;
		}
		
		Employment employment = null;
		switch(employee) {
		case "employee":
			employment = employment.EMPLOYEE;
			break;
		case "self-employee":
			employment = employment.SELFEMPLOYEE;
			break;
		case "unemployee":
			employment = employment.UNEMPLOYEE;
			break;
		case "other":
			employment = employment.OTHER;
			break;
		}
		
		Gender gender = null;
		switch(genderstr) {
		case "Male":
			gender = gender.MALE;
			break;
		case "Female":
			gender = gender.FEMALE;
			break;
		}
		Person person = new Person(name, occupation, ageCategory, employment, taxId, usCitizen, gender);
		database.addPerson(person);
	}

	
	public List<Person> getPersons() {
		return database.getPersons();
	}
}
