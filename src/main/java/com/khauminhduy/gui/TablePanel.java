package com.khauminhduy.gui;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.khauminhduy.event.PersonTableListener;
import com.khauminhduy.model.Person;

public class TablePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTable table;
	private PersonTableModel tableModel;
	private JPopupMenu popup;
	private JMenuItem removeItem;
	private JMenuItem editItem;
	
	private PersonTableListener personTableListener;

	public TablePanel() {
		setControl();
		setEvent();
		setProperties();
	}

	private void setControl() {
		tableModel = new PersonTableModel();
		table = new JTable(tableModel);
		popup = new JPopupMenu();
		
		removeItem = new JMenuItem("Delete Row");
		editItem = new JMenuItem("Edit Row");
		popup.add(editItem);
		popup.add(removeItem);
		
	}

	private void setEvent() {
		table.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON3) {
					int row = table.rowAtPoint(e.getPoint());
					
					table.getSelectionModel().setSelectionInterval(row, row);
					
					popup.show(table, e.getX(), e.getY());
				}
			}
			
		});
		
		removeItem.addActionListener(event -> {
			int row = table.getSelectedRow();
			if(personTableListener != null) {
				personTableListener.rowDelete(row);
				tableModel.fireTableRowsDeleted(row, row);
			}
		});
		
	}

	private void setProperties() {
		setLayout(new BorderLayout());
		add(new JScrollPane(table), BorderLayout.CENTER);
	}
	
	public void setData(List<Person> db) {
		tableModel.setData(db);
	}

	public void refresh() {
		tableModel.fireTableDataChanged();
	}

	public void addPersonTableListener(PersonTableListener personTableListener) {
		this.personTableListener = personTableListener;
	}

}
