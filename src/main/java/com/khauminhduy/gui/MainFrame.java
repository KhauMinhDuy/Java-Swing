package com.khauminhduy.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;

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

	private TextPanel textPanel;
	private ToolBar toolBar;
	private FormPanel formPanel;
	private TablePanel tablePanel;
	
	private JFileChooser fileChooser;
	
	private Controller controller;

	public MainFrame() {
		super("Hello World");
		setControl();
		setEvent();
		setProperties();
	}

	private void setProperties() {
		setLayout(new BorderLayout());
		add(toolBar, BorderLayout.NORTH);
		add(tablePanel, BorderLayout.CENTER);
		add(formPanel, BorderLayout.WEST);

		setJMenuBar(createMenu());
		setMinimumSize(new Dimension(Const.WIDTH, Const.HEIGHT));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void setEvent() {
		toolBar.setStringListener(text -> {
			textPanel.append(text);
		});

		formPanel.addFormEventOccured(event -> {
			controller.addPerson(event);
			tablePanel.refresh();
		});
	}

	private void setControl() {
		textPanel = new TextPanel();
		
		toolBar = new ToolBar();
		
		formPanel = new FormPanel();
		
		tablePanel = new TablePanel();
		
		fileChooser = new JFileChooser();
		fileChooser.addChoosableFileFilter(new FileExtenstions("txt"));
		fileChooser.addChoosableFileFilter(new FileExtenstions("pdf"));
		
		controller = new Controller();
		
		tablePanel.setData(controller.getPersons());
		
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
		exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		newItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		menu.add(fileMenu);
		menu.add(windowMenu);

		newItem.addActionListener(event -> {
			System.out.println(newItem.getText());
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

		return menu;
	}

}
