package com.javapoints.layout.boxlayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

public class BoxLayoutXAxisDemo {

	private JFrame frame;
	
	public BoxLayoutXAxisDemo() {
		frame = new JFrame();
		
		JButton[] buttons = new JButton[5];
		
		for(int i = 0; i < 5; i++) {
			buttons[i] = new JButton("Button " + (i + 1));
			frame.add(buttons[i]);
		}
		
		frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setSize(300, 300);
		frame.pack();
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		new BoxLayoutXAxisDemo();
	}
	
}
