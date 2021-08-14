package com.khauminhduy.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
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

	public void addPerson(FormEvent event) throws SQLException {
		database.save(toPerson(event));
	}

	public List<Person> getPersons() throws SQLException {
		return database.getAll();
	}

	public void saveToFile(File file) throws IOException, SQLException {
		database.saveToFile(file);
	}

	public void loadToFile(File file) throws ClassNotFoundException, IOException, SQLException {
		database.loadToFile(file);
	}

	public void removePerson(int index) throws SQLException {
		database.removePerson(index);
	}

	private Person toPerson(FormEvent event) {
		String name = event.getName();
		String occupation = event.getOccupation();
		String ageCat = event.getAgeCategory();
		String employee = event.getEmployee();
		String taxId = event.getTaxId();
		boolean usCitizen = event.isUsCitizen();
		String genderstr = event.getGender();
	
		AgeCategory ageCategory = toAgeCategory(ageCat);
		Employment employment = toEmployment(employee);
		Gender gender = toGender(genderstr);
	
		return new Person(name, occupation, ageCategory, employment, taxId, usCitizen, gender);
	}

	private AgeCategory toAgeCategory(String ageCat) {
		switch (ageCat) {
		case "Under 18":
			return AgeCategory.CHILD;
		case "18 to 69":
			return AgeCategory.ADULT;
		case "69 or over":
			return AgeCategory.SENIOR;
		}
		return null;
	}

	private Employment toEmployment(String employee) {
		switch (employee) {
		case "employee":
			return Employment.EMPLOYEE;
		case "self-employee":
			return Employment.SELFEMPLOYEE;
		case "unemployee":
			return Employment.UNEMPLOYEE;
		case "other":
			return Employment.OTHER;
		}
		return null;
	}

	private Gender toGender(String genderstr) {
		switch (genderstr) {
		case "Male":
			return Gender.MALE;
		case "Female":
			return Gender.FEMALE;
		}
		return null;
	}

}
