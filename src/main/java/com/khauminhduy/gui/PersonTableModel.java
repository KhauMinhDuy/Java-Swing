package com.khauminhduy.gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.khauminhduy.model.Person;

public class PersonTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	private List<Person> db;

	private String[] colName = { "ID", "Name", "Occupation", "Age", "Employee", "Citizen", "Tax Id", "Gender" };

	public void setData(List<Person> db) {
		this.db = db;
	}

	@Override
	public String getColumnName(int column) {
		return colName[column];
	}

	@Override
	public int getColumnCount() {
		return colName.length;
	}

	@Override
	public int getRowCount() {
		return db.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		Person person = db.get(row);
		switch (col) {
		case 0:
			return person.getId();
		case 1:
			return person.getName();
		case 2:
			return person.getOccupation();
		case 3:
			return person.getAgeCategory();
		case 4:
			return person.getEmployee();
		case 5:
			return person.isUsCitizen();
		case 6:
			return person.getTaxId();
		case 7:
			return person.getGender();
		}
		return null;
	}

}
