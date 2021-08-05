package com.khauminhduy.db;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
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
	
	public void saveToFile(File file) throws IOException {
		try(FileOutputStream fileOutputStream = new FileOutputStream(file);
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);) {
			
			Person[] people = persons.toArray(new Person[persons.size()]);
			
			objectOutputStream.writeObject(people);
		}
	}
	
	public void loadToFile(File file) throws IOException, ClassNotFoundException {
		try(FileInputStream fileInputStream = new FileInputStream(file);
				ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);) {
			
			Person[] people = (Person[]) objectInputStream.readObject();
			persons.clear();
			
			persons.addAll(Arrays.asList(people));
			
		}
		
	}
}
