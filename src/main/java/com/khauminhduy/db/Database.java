package com.khauminhduy.db;

import java.util.ArrayList;
import java.util.List;

import com.khauminhduy.model.Person;

public class Database {

	private List<Person> persons;

	public Database() {
		persons = new ArrayList<>();
	}

	public void addPerson(Person person) {
		persons.add(person);
	}

	public List<Person> getPersons() {
		return persons;
	}
}
