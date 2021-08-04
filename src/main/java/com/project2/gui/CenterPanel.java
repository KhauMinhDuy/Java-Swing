package com.project2.gui;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class CenterPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTextArea textArea;

	public CenterPanel() {
		setControl();
		setProperties();
	}

	private void setControl() {
		textArea = new JTextArea(10, 20);
		textArea.setText("Content");
	}

	private void setProperties() {
		textArea.setEditable(false);
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(textArea);
		
	}

}
