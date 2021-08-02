package com.khauminhduy.swing;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.khauminhduy.event.StringListener;

public class ToolBar extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 1L;

	private JButton helloBtn;
	private JButton goodbyeBtn;
	
	private StringListener stringListener;
	
	public ToolBar() {
		setControl();
		setEvent();
		setProperties();
	}

	private void setProperties() {
		setLayout(new FlowLayout(FlowLayout.LEFT));
		setBorder(BorderFactory.createEtchedBorder());
		add(helloBtn);
		add(goodbyeBtn);
	}

	private void setEvent() {
		helloBtn.addActionListener(this);
		goodbyeBtn.addActionListener(this);
	}

	private void setControl() {
		helloBtn = new JButton("Hello");
		goodbyeBtn = new JButton("Goodbye");
	}
	
	public void setStringListener(StringListener stringListener) {
		this.stringListener = stringListener;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button =  (JButton) e.getSource();
		if(button == helloBtn) {
			if(stringListener != null) {
				stringListener.textEmitted("Hello\n");
			}
		} else if(button == goodbyeBtn) {
			if(stringListener != null) {
				stringListener.textEmitted("Goodbye\n");
			}
		}
	}

}
