package com.khauminhduy.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;

import com.khauminhduy.event.PrefsListener;

public class PrefsDialog extends JDialog {
	
	private static final int FIELDLENGHT = 15;

	private static final long serialVersionUID = 1L;

	private JLabel portLabel;
	private JLabel userLabel;
	private JLabel passLabel;
	
	private JTextField userField;
	private JPasswordField passField;
	
	private JButton okButton;
	private JButton cancelButton;
	private JSpinner portSpinner;
	private SpinnerNumberModel spinnerNumberModel;
	
	private JPanel controlPanel;
	private JPanel buttonPanel;
	
	private PrefsListener prefsListener;

	public PrefsDialog(JFrame parent) {
		super(parent, "Preferences", false);
		setControl();
		setEvent();
		setProperties(parent);
	}

	private void setControl() {
		portLabel = new JLabel("Port: ");
		userLabel = new JLabel("Username: ");
		passLabel = new JLabel("Password: ");
		
		userField = new JTextField(FIELDLENGHT);
		passField = new JPasswordField(FIELDLENGHT);
		
		okButton = new JButton("OK");
		cancelButton = new JButton("Cancel");
		spinnerNumberModel = new SpinnerNumberModel(3306, 0, 9999, 1);
		portSpinner = new JSpinner(spinnerNumberModel);
	}

	private void setEvent() {
		okButton.addActionListener(event -> {
			int port = (int) portSpinner.getValue();
			String username = userField.getText();
			String password = new String(passField.getPassword());
			if(prefsListener != null) {
				prefsListener.preferencesSet(username, password, port);
			}
			setVisible(false);
		});
		
		cancelButton.addActionListener(event -> {
			setVisible(false);
		});
	}
	
	private void setProperties(JFrame parent) {
		setLayout(new BorderLayout());
		layoutControl();
		add(controlPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
		
		setSize(400, 300);	
		setLocationRelativeTo(parent);
	}

	private void layoutControl() {
		
		controlPanel = new JPanel();
		buttonPanel = new JPanel();
		
		Border emptyBorder = BorderFactory.createEmptyBorder(15, 15, 15, 15);
		Border titleBorder = BorderFactory.createTitledBorder("Database Connect");
		controlPanel.setBorder(BorderFactory.createCompoundBorder(emptyBorder, titleBorder));
		
		buttonPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		controlPanel.setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		
		// Row 1
		gc.gridy = 0;
		gc.gridx = 0;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		controlPanel.add(userLabel, gc);
		
		gc.gridx = 1;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		controlPanel.add(userField, gc);
		
		// Row 2
		gc.gridy++;
		gc.gridx = 0;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.anchor = GridBagConstraints.LINE_END;
		controlPanel.add(passLabel, gc);
		
		gc.gridx = 1;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		controlPanel.add(passField, gc);
		
		// Row 3
		gc.gridy++;
		gc.gridx = 0;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.anchor = GridBagConstraints.LINE_END;
		controlPanel.add(portLabel, gc);
		
		gc.gridx = 1;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		controlPanel.add(portSpinner, gc);
		
		Dimension cancelSize = cancelButton.getPreferredSize();
		okButton.setPreferredSize(cancelSize);
		
		// Row 4
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		buttonPanel.add(okButton);
		buttonPanel.add(cancelButton);
		
	}

	public void setPreferences(PrefsListener prefsListener) {
		this.prefsListener = prefsListener;
	}
	
	public void setDefault(String username, String password, int port) {
		userField.setText(username);
		passField.setText(password);
		portSpinner.setValue(port);
	}
	
}
