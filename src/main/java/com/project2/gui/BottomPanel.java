package com.project2.gui;

import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.BoxLayout;

public class BottomPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton spHetHanBtn;

	public BottomPanel() {

		setControl();
		setEvent();
		setProperties();

	}

	private void setControl() {
		spHetHanBtn = new JButton("San Pham Het Han");
	}

	private void setEvent() {
		spHetHanBtn.addActionListener(event -> {
			System.out.println("Click");
			new SPHetHan();
		});
	}

	private void setProperties() {
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		add(spHetHanBtn);
	}

}
