package com.khauminhduy.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

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
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		
		// Row 1
		gc.gridy = 0;
		gc.gridx = 0;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.anchor = GridBagConstraints.LINE_END;
		add(userLabel, gc);
		
		gc.gridx = 1;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		add(userField, gc);
		
		// Row 2
		gc.gridy++;
		gc.gridx = 0;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.anchor = GridBagConstraints.LINE_END;
		add(passLabel, gc);
		
		gc.gridx = 1;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		add(passField, gc);
		
		// Row 3
		gc.gridy++;
		gc.gridx = 0;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		add(portLabel, gc);
		
		gc.gridx = 1;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		add(portSpinner, gc);
		
		// Row 4
		gc.gridy++;
		gc.gridx = 0;
		gc.weightx = 1;
		gc.weighty = 10;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(okButton, gc);
		
		gc.gridx = 1;
		gc.weightx = 1;
		gc.weighty = 10;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(cancelButton, gc);
		
		setSize(400, 300);	
		setLocationRelativeTo(parent);
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
