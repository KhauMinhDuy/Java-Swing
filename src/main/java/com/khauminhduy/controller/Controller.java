package com.khauminhduy.controller;

import com.khauminhduy.event.FormEvent;

public class Controller {

	public Controller() {

	}

	public void addPerson(FormEvent event) {
		String name = event.getName();
		String occupation = event.getOccupation();
		String age = event.getAge();
		String employee = event.getEmployee();
		String taxId = event.getTaxId();
		boolean usCitizen = event.isUsCitizen();
		String gender = event.getGender();
		
		
	}

}
