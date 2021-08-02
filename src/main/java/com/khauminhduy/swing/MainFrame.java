package com.khauminhduy.swing;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;

import javax.sql.rowset.serial.SQLOutputImpl;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.khauminhduy.consts.Const;

public class MainFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private TextPanel textPanel;
	private ToolBar toolBar;
	private JButton button;
	private FormPanel formPanel;
	
	public MainFrame() {
		super("Hello World");
		setControl();
		setEvent();
		setProperties();
	}

	private void setProperties() {
		setLayout(new BorderLayout());
		add(toolBar, BorderLayout.NORTH);
		add(textPanel, BorderLayout.CENTER);
		add(button, BorderLayout.SOUTH);
		add(formPanel, BorderLayout.WEST);
		
		setJMenuBar(createMenu());
		setSize(Const.WIDTH, Const.HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void setEvent() {
		toolBar.setStringListener(text -> {
			textPanel.append(text);
		});

		button.addActionListener(event -> {
			textPanel.append("Click\n");
		});
		
		formPanel.addFormEventOccured(event -> {
			String name = event.getName();
			String occupation = event.getOccupation();
			String age = event.getAge();
			String employee = event.getEmployee();
			String taxId = event.getTaxId();
			boolean usCitizen = event.isUsCitizen();
			String gender = event.getGender();
			
			textPanel.append("Name : " + name + " | " + "Occupation: " + occupation + " | " + "Age: " + age + " | "
					+ "Employment: " + employee + " | " + taxId + " | " + usCitizen + " | " + gender + "\n");
			
		});
	}

	private void setControl() {
		textPanel = new TextPanel();
		toolBar = new ToolBar();
		button = new JButton("Click");
		formPanel = new FormPanel();
	}
	
	private JMenuBar createMenu() {
		JMenuBar menu = new JMenuBar();
		
		JMenu fileMenu = new JMenu("File");
		JMenuItem newItem = new JMenuItem("New File");
		JMenuItem exportDataItem = new JMenuItem("Export Data");
		JMenuItem importDataItem = new JMenuItem("Import Data");
		JMenuItem exitItem = new JMenuItem("Exit");
		
		fileMenu.add(newItem);
		fileMenu.addSeparator();
		fileMenu.add(exportDataItem);
		fileMenu.add(importDataItem);
		fileMenu.addSeparator();
		fileMenu.add(exitItem);
		
		
		
		JMenu windowMenu = new JMenu("Window");
		JMenuItem newWindowItem = new JMenuItem("New Window");
		windowMenu.add(newWindowItem);
		
		
		JMenu showMenu = new JMenu("Show");
		JCheckBoxMenuItem showFormCheckBox = new JCheckBoxMenuItem("Show Form");
		showMenu.add(showFormCheckBox);
		showFormCheckBox.setSelected(true);
		windowMenu.add(showMenu);
		
		fileMenu.setMnemonic(KeyEvent.VK_F);
		windowMenu.setMnemonic(KeyEvent.VK_W);
		exitItem.setMnemonic(KeyEvent.VK_X);
		
		menu.add(fileMenu);
		menu.add(windowMenu);
		
		newItem.addActionListener(event -> {
			System.out.println(newItem.getText());
		});
		
		exportDataItem.addActionListener(event -> {
			System.out.println(exportDataItem.getText());
		});
		
		importDataItem.addActionListener(event -> {
			System.out.println(importDataItem.getText());
		});
		
		exitItem.addActionListener(event -> {
			System.out.println(exitItem.getText());
			System.exit(0);
		});
		
		showFormCheckBox.addActionListener(event -> {
			JCheckBoxMenuItem form =  (JCheckBoxMenuItem) event.getSource();
			formPanel.setVisible(form.isSelected());
		});
		
		return menu;
	}
	
}
