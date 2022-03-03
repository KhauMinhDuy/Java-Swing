package com.javapoints.layout.gridbaglayout;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class GridBagLayoutDemo {

	private JFrame frame;
	
	public GridBagLayoutDemo() {
		frame = new JFrame();
		
		GridBagLayout grid = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		
		frame.setLayout(grid);
		
		frame.setTitle("GridGabLayout Demo");
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		frame.add(new JButton("One"), gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		frame.add(new JButton("Two"), gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;  
	    gbc.ipady = 20;  
	    gbc.gridx = 0;  
	    gbc.gridy = 1;  
	    frame.add(new JButton("Button Three"), gbc);  
	    gbc.gridx = 1;  
	    gbc.gridy = 1;  
	    frame.add(new JButton("Button Four"), gbc);  
	    gbc.gridx = 0;  
	    gbc.gridy = 2;  
	    gbc.fill = GridBagConstraints.HORIZONTAL;  
	    gbc.gridwidth = 2;  
		frame.add(new JButton("Five"), gbc);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setSize(300, 300);
		frame.setVisible(true);
		
		
	}
	
	public static void main(String[] args) {
		new GridBagLayoutDemo();
	}
	
}
