package com.javapoints.layout.cardlayout;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class CardLayoutDemo2 implements ActionListener{

	private JFrame frame;
	
	private CardLayout cardLayout;
	
	private JButton b1, b2, b3;
	
	public CardLayoutDemo2() {
		frame = new JFrame();
		
		cardLayout = new CardLayout();
		
		b1 = new JButton("b1");
		b2 = new JButton("b2");
		b3 = new JButton("b3");
		
		frame.add(b1);
		frame.add(b2);
		frame.add(b3);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		
		frame.setLayout(cardLayout);
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
		new CardLayoutDemo2();
	}

	
	
}
