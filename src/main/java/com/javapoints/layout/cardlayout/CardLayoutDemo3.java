package com.javapoints.layout.cardlayout;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CardLayoutDemo3 {

	private JFrame frame;
	
	private CardLayout cardLayout;
	private int currentCard = 1;
	
	public CardLayoutDemo3() {
		frame = new JFrame();
		
		cardLayout = new CardLayout();
		
		frame.setTitle("Card Layout Demo");
		
		frame.setSize(310, 160);
		
		JPanel panelMain = new JPanel();
		
		panelMain.setLayout(cardLayout);
		
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		JPanel panel4 = new JPanel();
		
		JLabel label1 = new JLabel("C1");
		JLabel label2 = new JLabel("C2");
		JLabel label3 = new JLabel("C3");
		JLabel label4 = new JLabel("C4");
		
		panel1.add(label1);
		panel2.add(label2);
		panel3.add(label3);
		panel4.add(label4);
		
		panelMain.add(panel1, "1");
		panelMain.add(panel2, "2");
		panelMain.add(panel3, "3");
		panelMain.add(panel4, "4");
		
		JPanel btnPanel = new JPanel();
		JButton firstButton = new JButton("First");  
		JButton nextButton = new JButton("->");  
		JButton previousButton = new JButton("<-");  
		JButton lastButton = new JButton("Last");  
		
		btnPanel.add(firstButton);  
		btnPanel.add(nextButton);  
		btnPanel.add(previousButton);  
		btnPanel.add(lastButton);  
		
		firstButton.addActionListener((ActionEvent e) -> {
			cardLayout.first(panelMain);
			currentCard = 1;
		});
		
		lastButton.addActionListener((ActionEvent e) -> {
			cardLayout.last(panelMain);
			currentCard = 4;
		});
		
		nextButton.addActionListener((ActionEvent e) -> {
			if(currentCard < 4) {
				currentCard += 1;
				cardLayout.show(panelMain, String.valueOf(currentCard));
			}
		});
		
		previousButton.addActionListener((ActionEvent e) -> {
			if(currentCard > 1) {
				currentCard -= 1;
				cardLayout.show(panelMain, String.valueOf(currentCard));
			}
		});
		
		frame.add(panelMain,BorderLayout.NORTH);
		frame.add(btnPanel, BorderLayout.SOUTH);
		

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setSize(300, 300);
		frame.setVisible(true);
		  
		
	}
	
	public static void main(String[] args) {
		new CardLayoutDemo3();
	}
	
}
