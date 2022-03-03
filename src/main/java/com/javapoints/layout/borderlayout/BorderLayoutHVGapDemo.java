package com.javapoints.layout.borderlayout;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class BorderLayoutHVGapDemo {

	private JFrame frame;

	public BorderLayoutHVGapDemo() {

		frame = new JFrame();

		JButton btn1 = new JButton("NORTH");
		JButton btn2 = new JButton("SOUTH");
		JButton btn3 = new JButton("EAST");
		JButton btn4 = new JButton("WEST");
		JButton btn5 = new JButton("CENTER");

		frame.setLayout(new BorderLayout(20, 15));
		frame.add(btn1, BorderLayout.NORTH);
		frame.add(btn2, BorderLayout.SOUTH);
		frame.add(btn3, BorderLayout.EAST);
		frame.add(btn4, BorderLayout.WEST);
		frame.add(btn5, BorderLayout.CENTER);

		frame.setSize(300, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		new BorderLayoutHVGapDemo();
	}

}
