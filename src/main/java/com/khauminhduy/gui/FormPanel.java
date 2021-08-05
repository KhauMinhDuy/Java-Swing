package com.khauminhduy.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;

import com.khauminhduy.event.FormEvent;
import com.khauminhduy.event.FormListener;

public class FormPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JLabel name;
	private JLabel occupationLabel;
	private JLabel ageLabel;
	private JLabel empLabel;
	private JLabel citizenLabel;
	private JLabel taxLabel;

	private JTextField nameField;
	private JTextField occupationField;
	private JTextField taxField;

	private JButton okBtn;

	private JList<String> ageList;

	private JComboBox<String> empCombo;

	private JCheckBox citizenCheck;

	private JRadioButton maleRadio;
	private JRadioButton femaleRadio;
	private ButtonGroup genderGroup;

	private FormListener formListener;

	public FormPanel() {
		setControl();
		setEvent();
		setProperties();
	}

	private void setControl() {
		name = new JLabel("Name: ");
		occupationLabel = new JLabel("Occupation: ");
		ageLabel = new JLabel("Age: ");
		empLabel = new JLabel("Employment: ");
		citizenLabel = new JLabel("US Citizen: ");
		taxLabel = new JLabel("Tax Id: ");
		
		nameField = new JTextField(10);
		occupationField = new JTextField(10);
		taxField = new JTextField(10);
		
		okBtn = new JButton("OK");
		
		citizenCheck = new JCheckBox();
		
		maleRadio = new JRadioButton("Male");
		maleRadio.setActionCommand("Male");
		femaleRadio = new JRadioButton("Female");
		femaleRadio.setActionCommand("Female");
		genderGroup = new ButtonGroup();
		genderGroup.add(maleRadio);
		genderGroup.add(femaleRadio);
		maleRadio.setSelected(true);

		ageList = new JList<>();
		DefaultListModel<String> ageModel = new DefaultListModel<>();
		ageModel.addElement("Under 18");
		ageModel.addElement("18 to 69");
		ageModel.addElement("69 or over");
		ageList.setModel(ageModel);
		ageList.setPreferredSize(new Dimension(110, 60));
		ageList.setBorder(BorderFactory.createEtchedBorder());
		ageList.setSelectedIndex(0);

		empCombo = new JComboBox<>();
		DefaultComboBoxModel<String> empModel = new DefaultComboBoxModel<>();
		empModel.addElement("employee");
		empModel.addElement("self-employee");
		empModel.addElement("unemployee");
		empCombo.setModel(empModel);
		empCombo.setSelectedIndex(0);

		taxField.setEnabled(false);
		taxLabel.setEnabled(false);
		okBtn.setMnemonic(KeyEvent.VK_ENTER);
		name.setDisplayedMnemonic(KeyEvent.VK_N);
		name.setLabelFor(nameField);
		occupationLabel.setDisplayedMnemonic(KeyEvent.VK_O);
		occupationLabel.setLabelFor(occupationField);
		ageLabel.setDisplayedMnemonic(KeyEvent.VK_A);
		ageLabel.setLabelFor(ageList);

	}

	private void setEvent() {
		okBtn.addActionListener(event -> {
			String name = nameField.getText();
			String occupation = occupationField.getText();
			String age = ageList.getSelectedValue();
			String employee = (String) empCombo.getSelectedItem();
			boolean selected = citizenCheck.isSelected();
			String taxId = taxField.getText();
			String gender = genderGroup.getSelection().getActionCommand();
			FormEvent formEvent = new FormEvent(this, name, occupation, age, employee, taxId, selected, gender);
			if (formListener != null) {
				formListener.FormEventOccured(formEvent);
			}

		});

		citizenCheck.addActionListener(event -> {
			boolean isSelected = citizenCheck.isSelected();
			taxLabel.setEnabled(isSelected);
			taxField.setEnabled(isSelected);
		});
	}

	private void setProperties() {
		Dimension dimension = getPreferredSize();
		dimension.width = 250;
		setPreferredSize(dimension);

		Border innerBorder = BorderFactory.createTitledBorder("Add Person");
		Border outnerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outnerBorder, innerBorder));

		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();

		// Row 1

		gc.gridx = 0;
		gc.gridy = 0;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(name, gc);

		gc.gridx = 1;
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(nameField, gc);

		// Row 2

		gc.gridy++;

		gc.gridx = 0;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(occupationLabel, gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(occupationField, gc);

		// Row 3

		gc.gridy++;
		gc.gridx = 0;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(ageLabel, gc);

		gc.gridx = 1;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(ageList, gc);

		// Row 4

		gc.gridy++;
		gc.gridx = 0;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(empLabel, gc);

		gc.gridx = 1;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(empCombo, gc);

		// Row 5

		gc.gridy++;
		gc.gridx = 0;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(citizenLabel, gc);

		gc.gridx = 1;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(citizenCheck, gc);

		// Row 6

		gc.gridy++;
		gc.gridx = 0;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(taxLabel, gc);

		gc.gridx = 1;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(taxField, gc);

		// Row 7

		gc.gridy++;
		gc.gridx = 0;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(5, 0, 0, 5);
		add(new JLabel("Gender: "), gc);

		gc.gridx = 1;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(maleRadio, gc);

		gc.gridy++;
		gc.gridx = 1;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(femaleRadio, gc);

		// Row 8

		gc.gridy++;

		gc.gridx = 1;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(okBtn, gc);
	}

	public void addFormEventOccured(FormListener formListener) {
		this.formListener = formListener;
	}

}
