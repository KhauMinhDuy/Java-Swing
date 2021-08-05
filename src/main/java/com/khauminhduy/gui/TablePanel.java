package com.khauminhduy.gui;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTable;

import com.khauminhduy.model.Person;

public class TablePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTable table;
	private PersonTableModel tableModel;

	public TablePanel() {
		setControl();
		setEvent();
		setProperties();
	}

	private void setControl() {
		tableModel = new PersonTableModel();
		table = new JTable(tableModel);
	}

	private void setEvent() {

	}

	private void setProperties() {
		setLayout(new BorderLayout());
		add(table, BorderLayout.CENTER);
	}
	
	public void setData(List<Person> db) {
		tableModel.setData(db);
	}

}
