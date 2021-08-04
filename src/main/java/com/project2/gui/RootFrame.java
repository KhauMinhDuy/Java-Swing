package com.project2.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class RootFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private CenterPanel centerPanel;
	private BottomPanel bottomPanel;

	public RootFrame() {
		super("Home");
		setControl();

		setProperties();
	}

	private void setControl() {
		centerPanel = new CenterPanel();
		bottomPanel = new BottomPanel();
	}

	private void setProperties() {
		setLayout(new BorderLayout());

		add(centerPanel, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);
		
		setSize(600, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

}
