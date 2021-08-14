package com.khauminhduy.db;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.khauminhduy.model.AgeCategory;
import com.khauminhduy.model.Employment;
import com.khauminhduy.model.Gender;
import com.khauminhduy.model.Person;

public class Database {

	private static final String URL = "jdbc:h2:tcp://localhost/D:/Development/Java/h2/testdb";
	private static final String USER = "sa";
	private static final String PASS = "";
	
	private static final String INSERT_PERSON = "INSERT INTO Person(name, age, employement, tax_id, us_citizen, occupation) "
			+ "VALUES(?, ?, ?, ?, ?, ?);";
	private static final String GETALL_PERSON = "SELECT * FROM Person;";
	private static final String UPDATE_PERSON = "UPDATE PERSON SET ";
	private static final String DELETE_PERSON = "DELETE PERSON WHERE id = ?;";
	
	
	private List<Person> persons;

	public Database() {
		persons = new ArrayList<>();
	}
	
	public void save(Person person) throws SQLException {
		try(Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(INSERT_PERSON);) {
			statement.setString(1, person.getName());
			statement.setString(2, person.getAgeCategory().toString());
			statement.setString(3, person.getEmployee().toString());
			statement.setString(4, person.getTaxId());
			statement.setBoolean(5, person.isUsCitizen());
			statement.setString(6, person.getOccupation());
			
			int executeUpdate = statement.executeUpdate();
			if(executeUpdate != 0) {
				persons = getAll();
			}
		}
	}
	
	public List<Person> getAll() throws SQLException {
		persons.clear();
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(GETALL_PERSON);
				ResultSet resultSet = statement.executeQuery();) {
			while(resultSet.next()) {
				persons.add(toPerson(resultSet));
			}
		}
		return persons;
	}
	
	public void update(Person person) throws SQLException {
		try(Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_PERSON);) {
			
			statement.executeUpdate();
			
		}
	}
	
	public void removePerson(int index) throws SQLException {
		int id = getAll().get(index).getId();
		try(Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_PERSON);) {
			statement.setInt(1, id);
			int executeUpdate = statement.executeUpdate();
			if(executeUpdate != 0) {
				persons = getAll();
			}
		}
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

	private Person toPerson(ResultSet resultSet) throws SQLException {
		int id = resultSet.getInt("id");
		String name = resultSet.getString("name");
		String age = resultSet.getString("age");
		String employmentstr = resultSet.getString("employement");
		String taxId = resultSet.getString("tax_id");
		String citizen = resultSet.getString("us_citizen");
		boolean parseBoolean = Boolean.parseBoolean(citizen);
		String occupation = resultSet.getString("occupation");
		
		AgeCategory ageCategory = toAgecategory(age);
		Employment employment = toEmployment(employmentstr);
		
		Person person = new Person(name, occupation, ageCategory, employment, taxId, parseBoolean, Gender.MALE);
		person.setId(id);
		return person;
	}

	private Employment toEmployment(String employmentstr) {
		switch(employmentstr) {
		case "EMPLOYEE":
			return Employment.EMPLOYEE;
		case "SELFEMPLOYEE":
			return Employment.SELFEMPLOYEE;
		case "UNEMPLOYEE":
			return Employment.UNEMPLOYEE;
		case "OTHER":
			return Employment.OTHER;
		}
		return null;
	}

	private AgeCategory toAgecategory(String age) {
		switch(age) {
		case "CHILD":
			return AgeCategory.CHILD;
		case "ADULT":
			return AgeCategory.ADULT;
		case "SENIOR":
			return AgeCategory.SENIOR;
		}
		return null;
	}

	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USER, PASS);
	}
}
