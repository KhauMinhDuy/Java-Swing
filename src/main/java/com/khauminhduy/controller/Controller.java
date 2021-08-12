package com.khauminhduy.controller;

import java.io.File;
import java.io.IOException;
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
			ageCategory = AgeCategory.CHILD;
			break;
		case "18 to 69":
			ageCategory = AgeCategory.ADULT;
			break;
		case "69 or over":
			ageCategory = AgeCategory.SENIOR;
			break;
		}
		
		Employment employment = null;
		switch(employee) {
		case "employee":
			employment = Employment.EMPLOYEE;
			break;
		case "self-employee":
			employment = Employment.SELFEMPLOYEE;
			break;
		case "unemployee":
			employment = Employment.UNEMPLOYEE;
			break;
		case "other":
			employment = Employment.OTHER;
			break;
		}
		
		Gender gender = null;
		switch(genderstr) {
		case "Male":
			gender = Gender.MALE;
			break;
		case "Female":
			gender = Gender.FEMALE;
			break;
		}
		Person person = new Person(name, occupation, ageCategory, employment, taxId, usCitizen, gender);
		database.addPerson(person);
	}

	
	public List<Person> getPersons() {
		return database.getPersons();
	}
	
	public void saveToFile(File file) throws IOException {
		database.saveToFile(file);
	}
	
	public void loadToFile(File file) throws ClassNotFoundException, IOException {
		database.loadToFile(file);
	}

	public void removePerson(int row) {
		database.removePerson(row);
	}
	
}
