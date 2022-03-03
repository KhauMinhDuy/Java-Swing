package com.javapoints.layout.boxlayout;

import java.awt.ComponentOrientation;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

public class BoxLayoutLineAxisDemo {

	private JFrame frame;

	public BoxLayoutLineAxisDemo() {
		frame = new JFrame();

		JButton[] buttons = new JButton[5];

		frame.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		for (int i = 0; i < 5; i++) {
			buttons[i] = new JButton("Button " + (i + 1));
			frame.add(buttons[i]);
		}

		frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.LINE_AXIS));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setSize(300, 300);
		frame.pack();
		frame.setVisible(true);

	}

	public static void main(String[] args) {
		new BoxLayoutLineAxisDemo();
	}

}
