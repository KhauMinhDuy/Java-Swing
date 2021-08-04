package com.khauminhduy.gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TextPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private JTextArea textArea;

	public TextPanel() {
		setControl();
		setProperties();
	}

	private void setProperties() {
		setLayout(new BorderLayout());
		add(new JScrollPane(textArea), BorderLayout.CENTER);
	}

	private void setControl() {
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setEditable(false);
	}
	
	public void append(String msg) {
		textArea.append(msg);
	}

}
