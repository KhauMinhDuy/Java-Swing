package com.project1.gui;

import java.io.IOException;
import java.net.URL;

import javax.swing.BoxLayout;
import javax.swing.JEditorPane;
import javax.swing.JPanel;

import com.project1.App;

public class DisplayPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JEditorPane jEditorPane;
	private URL url;

	public DisplayPanel() {

		setControl();

	}

	private void setControl() {
		url = App.class.getResource("template.html");
		try {
			jEditorPane.setPage(url);
		} catch (IOException e) {
			jEditorPane.setContentType("text/html");
			jEditorPane.setText("<html>Page not found.</html>");
		}
		jEditorPane.setEditable(false);

		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
	}

}
