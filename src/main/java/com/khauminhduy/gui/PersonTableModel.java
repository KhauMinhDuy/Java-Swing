package com.khauminhduy.gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.khauminhduy.model.Person;

public class PersonTableModel extends AbstractTableModel{

	private static final long serialVersionUID = 1L;
	
	private List<Person> db;
	
	public void setData(List<Person> db) {
		this.db = db;
	}

	@Override
	public int getColumnCount() {
		
		return 0;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}
	


}
