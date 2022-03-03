package com.javapoints.layout.cardlayout;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class CardLayoutDemo implements ActionListener{

	private JFrame frame;
	
	private CardLayout cardLayout;
	
	private JButton btn1, btn2, btn3;
	
	public CardLayoutDemo() {
		frame = new JFrame();
		
		cardLayout = new CardLayout();
		
		frame.setLayout(cardLayout);
		
		btn1 = new JButton("B1");
		btn2 = new JButton("B2");
		btn3 = new JButton("B3");
		
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		btn3.addActionListener(this);
		
		frame.add("b1", btn1);
		frame.add("b2", btn2);
		frame.add("b3", btn3);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setSize(300, 300);
		frame.pack();
		frame.setVisible(true);
		
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		cardLayout.next(frame.getContentPane());
	}
	
	
	public static void main(String[] args) {
		new CardLayoutDemo();
	}

	
}
