package com.khauminhduy.gui;

import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

public class ToolBar extends JToolBar{
	
	private static final String VIEW_OFF_ICON = "src\\main\\resources\\7830774_view_off_icon.png";

	private static final String COG_ICON = "src\\main\\resources\\7830762_cog_icon.png";

	private static final long serialVersionUID = 1L;

	private JButton helloBtn;
	private JButton goodbyeBtn;
	
	
	public ToolBar() {
		setControl();
		setEvent();
		setProperties();
	}

	private void setControl() {
		helloBtn = new JButton("", createIcon(COG_ICON));
		goodbyeBtn = new JButton("", createIcon(VIEW_OFF_ICON));
	}

	private void setEvent() {
		helloBtn.addActionListener(event -> {
			System.out.println("Hello");
		});
		goodbyeBtn.addActionListener(event -> {
			System.out.println("Goodbye");
		});
	}

	private void setProperties() {
		setLayout(new FlowLayout(FlowLayout.LEFT));
		setBorder(BorderFactory.createEtchedBorder());
		
		setFloatable(false);
		
		helloBtn.setToolTipText("Setting");
		goodbyeBtn.setToolTipText("Hide");
		
		add(helloBtn);
		addSeparator();
		add(goodbyeBtn);
	}

	private ImageIcon createIcon(String path) {
		return new ImageIcon(path);
	}

}
