package com.project2.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SPHetHan extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JLabel sLApDungLabel;
	private JLabel loaiApDung;
	private JLabel discountPercent;
	private JLabel dateOrigin;
	private JLabel dateDes;
	
	private JTextField sLApDungField;
	private JTextField dateOriginField;
	private JTextField dateDesField;
	
	private JComboBox<String> loaiApDungCmB;
	private JComboBox<Integer> discountPercentCmB;
	
	private JPanel sLApDungPanel;
	
	public SPHetHan() {
		super("BHX-Pos :: Khai báo sản phẩm hết hạn, móp méo");
		setControl();
		setProperties();
	}

	private void setControl() {
		sLApDungLabel = new JLabel("Số Lượng Áp Dụng");
		loaiApDung = new JLabel("Loại Áp Dụng");
		discountPercent = new JLabel("%Giảm Giá");
		dateOrigin = new JLabel("Từ Ngày");
		dateDes = new JLabel("Đến Ngày");
		
		sLApDungField = new JTextField(20);
		dateOriginField = new JTextField(20);
		dateDesField = new JTextField(20);
		
		loaiApDungCmB = new JComboBox<>();
		DefaultComboBoxModel<String> boxModel = new DefaultComboBoxModel<>();
		boxModel.addElement("Móp méo");
		boxModel.addElement("Hết Hạn");
		loaiApDungCmB.setModel(boxModel);
		loaiApDungCmB.setPreferredSize(new Dimension(250, 30));
		
		discountPercentCmB = new JComboBox<>();
		DefaultComboBoxModel<Integer> boxModel2 = new DefaultComboBoxModel<>();
		boxModel2.addElement(10);
		boxModel2.addElement(11);
		boxModel2.addElement(12);
		discountPercentCmB.setModel(boxModel2);
		discountPercentCmB.setPreferredSize(new Dimension(250, 30));
		
		sLApDungPanel = new JPanel(new FlowLayout());
		
	}

	private void setProperties() {
		
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();

		// Row 1

		gc.gridx = 0;
		gc.gridy = 0;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 5, 0, 0);
		add(sLApDungLabel, gc);

		sLApDungPanel.add(sLApDungField);
		sLApDungPanel.add(new JLabel("(Hộp)"));
		
		gc.gridx = 1;
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(sLApDungPanel, gc);

		// Row 2

		gc.gridy++;

		gc.gridx = 0;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 5, 0, 0);
		add(loaiApDung, gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(loaiApDungCmB, gc);

		// Row 3

		gc.gridy++;
		gc.gridx = 0;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 5, 0, 0);
		add(discountPercent, gc);

		gc.gridx = 1;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(discountPercentCmB, gc);

		// Row 4

		gc.gridy++;
		gc.gridx = 0;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 5, 0, 0);
		add(dateOrigin, gc);

		gc.gridx = 1;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(dateOriginField, gc);

		// Row 5

		gc.gridy++;
		gc.gridx = 0;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 5, 0, 0);
		add(dateDes, gc);

		gc.gridx = 1;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(dateDesField, gc);

		
		setSize(600, 500);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(this);
		setVisible(true);
	}

}
