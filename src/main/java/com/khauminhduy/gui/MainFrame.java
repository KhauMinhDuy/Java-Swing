package com.khauminhduy.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.prefs.Preferences;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import com.khauminhduy.consts.Const;
import com.khauminhduy.controller.Controller;
import com.khauminhduy.util.FileExtenstions;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private FormPanel formPanel;
	private TablePanel tablePanel;

	private JFileChooser fileChooser;
	private PrefsDialog prefsDialog;

	private Controller controller;
	
	private JMenu fileMenu;
	private JMenu windowMenu;
	private JMenu showMenu;

	private JMenuItem prefsItem;
	private JMenuItem newWindowItem;
	private JMenuItem newItem;
	private JMenuItem exportDataItem;
	private JMenuItem importDataItem;
	private JMenuItem exitItem;

	private JMenuBar menu;

	private JCheckBoxMenuItem showFormCheckBox;
	
	private Preferences preferences;

	public MainFrame() {
		super("App");
		setControl();
		setEvent();
		setProperties();
	}

	private void setControl() {


		formPanel = new FormPanel();

		tablePanel = new TablePanel();

		fileChooser = new JFileChooser();
		fileChooser.addChoosableFileFilter(new FileExtenstions("txt"));
		fileChooser.addChoosableFileFilter(new FileExtenstions("pdf"));

		prefsDialog = new PrefsDialog(this);
		
		controller = new Controller();

		tablePanel.setData(controller.getPersons());
		
		menu = new JMenuBar();
		
		fileMenu = new JMenu("File");
		windowMenu = new JMenu("Window");
		showMenu = new JMenu("Show");
		
		newItem = new JMenuItem("New File");
		exportDataItem = new JMenuItem("Export Data");
		importDataItem = new JMenuItem("Import Data");
		exitItem = new JMenuItem("Exit");
		newWindowItem = new JMenuItem("New Window");
		prefsItem = new JMenuItem("Preferences...");
		
		showFormCheckBox = new JCheckBoxMenuItem("Show Form");
		
		preferences = Preferences.userRoot().node("db");
		String username = preferences.get("username", "");
		String password = preferences.get("password", "");
		int port = preferences.getInt("port", 3306);
		prefsDialog.setDefault(username, password, port);
	}

	private void setEvent() {

		formPanel.addFormEventOccured(event -> {
			controller.addPerson(event);
			tablePanel.refresh();
		});
		
		tablePanel.addPersonTableListener(row -> {
			controller.removePerson(row);
		});
		
		newItem.addActionListener(event -> {
			System.out.println(newItem.getText());
		});
		
		prefsItem.addActionListener(event -> {
			prefsDialog.setVisible(true);
		});
		
		exportDataItem.addActionListener(event -> {
			int openDialog = fileChooser.showOpenDialog(this);
			if (openDialog == JFileChooser.APPROVE_OPTION) {
				try {
					controller.saveToFile(fileChooser.getSelectedFile());
					System.out.println("Done");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		importDataItem.addActionListener(event -> {
			int openDialog = fileChooser.showOpenDialog(this);
			if (openDialog == JFileChooser.APPROVE_OPTION) {
				try {
					controller.loadToFile(fileChooser.getSelectedFile());
					tablePanel.refresh();
				} catch (IOException | ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		});

		exitItem.addActionListener(event -> {
			System.out.println(exitItem.getText());
			int showConfirmDialog = JOptionPane.showConfirmDialog(this, "Do you want exit", "Exit",
					JOptionPane.YES_NO_OPTION);
			if (showConfirmDialog == JOptionPane.YES_NO_OPTION) {
				System.exit(0);
			}
		});

		showFormCheckBox.addActionListener(event -> {
			JCheckBoxMenuItem form = (JCheckBoxMenuItem) event.getSource();
			formPanel.setVisible(form.isSelected());
		});
		
		prefsDialog.setPreferences((username, password, port) -> {
			preferences.put("username", username);
			preferences.put("password", password);
			preferences.putInt("port", port);
		});
		
	}

	private void setProperties() {
		setLayout(new BorderLayout());
		add(tablePanel, BorderLayout.CENTER);
		add(formPanel, BorderLayout.WEST);

		setJMenuBar(createMenu());
		setMinimumSize(new Dimension(Const.WIDTH, Const.HEIGHT));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private JMenuBar createMenu() {

		fileMenu.add(newItem);
		fileMenu.addSeparator();
		fileMenu.add(exportDataItem);
		fileMenu.add(importDataItem);
		fileMenu.addSeparator();
		fileMenu.add(exitItem);
		windowMenu.add(newWindowItem);
		showMenu.add(showFormCheckBox);
		windowMenu.add(showMenu);
		windowMenu.add(prefsItem);
		showFormCheckBox.setSelected(true);

		fileMenu.setMnemonic(KeyEvent.VK_F);
		windowMenu.setMnemonic(KeyEvent.VK_W);
		exitItem.setMnemonic(KeyEvent.VK_X);
		importDataItem.setMnemonic(KeyEvent.VK_I);
		exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		newItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		importDataItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));
		prefsItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
		menu.add(fileMenu);
		menu.add(windowMenu);

		return menu;
	}

}
