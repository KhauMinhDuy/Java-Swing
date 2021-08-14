package com.khauminhduy.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.prefs.Preferences;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.KeyStroke;

import com.khauminhduy.consts.Const;
import com.khauminhduy.controller.Controller;
import com.khauminhduy.util.FileExtenstions;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private FormPanel formPanel;
	private TablePanel tablePanel;
	private ToolBar toolBar;

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
	private JCheckBoxMenuItem showToolBar;
	
	private JSplitPane jSplitPane;
	
	private Preferences preferences;

	public MainFrame() throws SQLException {
		super("App");
		setControl();
		setEvent();
		setProperties();
	}

	private void setControl() throws SQLException {
		controller = new Controller();

		formPanel = new FormPanel();
		toolBar = new ToolBar();
		
		tablePanel = new TablePanel();
		tablePanel.setData(controller.getPersons());

		fileChooser = new JFileChooser();
		fileChooser.addChoosableFileFilter(new FileExtenstions("txt"));
		fileChooser.addChoosableFileFilter(new FileExtenstions("pdf"));

		prefsDialog = new PrefsDialog(this);
		
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
		showToolBar = new JCheckBoxMenuItem("Show ToolBar");
		
		preferences = Preferences.userRoot().node("db");
		String username = preferences.get("username", "");
		String password = preferences.get("password", "");
		int port = preferences.getInt("port", 3306);
		prefsDialog.setDefault(username, password, port);
		
		jSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, formPanel, tablePanel);
		jSplitPane.setOneTouchExpandable(true);
	}

	private void setEvent() {

		formPanel.addFormEventOccured(event -> {
			try {
				controller.addPerson(event);
				tablePanel.refresh();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		});
		
		tablePanel.addPersonTableListener(index -> {
			try {
				controller.removePerson(index);
			} catch (SQLException e) {
				e.printStackTrace();
			}
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
				} catch (IOException | SQLException e) {
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
				} catch (IOException | ClassNotFoundException | SQLException e) {
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
			if(form.isSelected()) {
				jSplitPane.setDividerLocation((int)formPanel.getMinimumSize().getWidth());
			}
			formPanel.setVisible(form.isSelected());
		});
		
		showToolBar.addActionListener(event -> {
			boolean selected = showToolBar.isSelected();
			toolBar.setVisible(selected);
		});
		
		prefsDialog.setPreferences((username, password, port) -> {
			preferences.put("username", username);
			preferences.put("password", password);
			preferences.putInt("port", port);
		});
		
		newWindowItem.addActionListener(event -> {
			try {
				new MainFrame();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		});
		
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				int showConfirmDialog = JOptionPane.showConfirmDialog(null, "Do you want exit", "Exit",
						JOptionPane.YES_NO_OPTION);
				if (showConfirmDialog == JOptionPane.YES_NO_OPTION) {
					System.exit(0);
				}
			}
			
		});
		
	}

	private void setProperties() {
		setLayout(new BorderLayout());
		
		
		
		add(toolBar, BorderLayout.PAGE_START);
		add(jSplitPane, BorderLayout.CENTER);
		
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
		showMenu.add(showToolBar);
		windowMenu.add(showMenu);
		windowMenu.add(prefsItem);
		
		showFormCheckBox.setSelected(true);
		showToolBar.setSelected(true);

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
