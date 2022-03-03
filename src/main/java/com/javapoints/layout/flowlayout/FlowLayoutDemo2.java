package com.javapoints.layout.flowlayout;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class FlowLayoutDemo2 {

	private JFrame frame;
	
	public FlowLayoutDemo2() {
		frame = new JFrame();
		
	    JButton b1=new JButton("1");    
	    JButton b2=new JButton("2");    
	    JButton b3=new JButton("3");    
	    JButton b4=new JButton("4");    
	    JButton b5=new JButton("5");    
	    
	    frame.add(b1);
	    frame.add(b2);
	    frame.add(b3);
	    frame.add(b4);
	    frame.add(b5);
	    
	    frame.setLayout(new FlowLayout(FlowLayout.RIGHT));
	    
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setSize(300, 300);
		frame.setVisible(true);
	    
	}
	
	public static void main(String[] args) {
		new FlowLayoutDemo2();
	}
	
}
