package com.project1.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import com.itextpdf.text.DocumentException;
import com.project1.event.FormEvent;
import com.project1.event.FormListener;
import com.project1.model.Bill;
import com.project1.util.HtmlToPdf;


public class FormPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private JLabel addressLabel;
	private JLabel soCTLabel;
	private JLabel dateTimeLabel;
	private JLabel empLabel;
	private JLabel giamGia;
	private JLabel phieuMuaHang;
	private JLabel tienThe;
	
	private JTextArea addressField;
	private JTextField soCTField;
	private JTextField dateTimeField;
	private JTextField empField;
	private JTextField giamGiaField;
	private JTextField phieuMHField;
	private JTextField tienTheField;
	
	private JButton luuBtn;
	private JButton inBtn;
	
	private JCheckBox giamGiaCheck;
	private JCheckBox phieuMHCheck;
	private JCheckBox tienTheCheck;
	
	private FormListener formListener;
	
	public FormPanel() {
		setControl();
		setEvent();
		setProperties();
	}
	
	private void setControl() {
		addressLabel = new JLabel("Địa Chỉ: ");
		soCTLabel = new JLabel("Số CT: ");
		dateTimeLabel = new JLabel("Ngày CT: ");
		empLabel = new JLabel("Nhân Viên: ");
		giamGia = new JLabel("Đã Giảm: ");
		phieuMuaHang = new JLabel("Phiếu Mua Hàng: ");
		tienThe = new JLabel("Tiền Cà Thẻ: ");
		
		addressField = new JTextArea(3, 20);
		soCTField = new JTextField(20);
		dateTimeField = new JTextField(20);
		empField = new JTextField(20);
		giamGiaField = new JTextField(20);
		phieuMHField = new JTextField(20);
		tienTheField = new JTextField(20);
		
		luuBtn = new JButton("Luu");
		inBtn = new JButton("IN");
		
		giamGiaCheck = new JCheckBox();
		phieuMHCheck = new JCheckBox();
		tienTheCheck = new JCheckBox();
		
	}

	private void setEvent() {
		luuBtn.addActionListener(event -> {
			
			String address = addressField.getText();
			String soCT = soCTField.getText();
			String dateTime = dateTimeField.getText();
			String emp = empField.getText();
			boolean giamGiaSelected = giamGiaCheck.isSelected();
			boolean phieuMHSelected = phieuMHCheck.isSelected();
			boolean tienTheSelected = tienTheCheck.isSelected();
			
			Bill bill = new Bill(address, soCT, dateTime, emp, 
					giamGiaSelected, phieuMHSelected, tienTheSelected);
			FormEvent formEvent = new FormEvent(this, bill);
			
			if(formListener != null) {
				formListener.formPerforment(formEvent);
			}
			
		});
		
		inBtn.addActionListener(event -> {
			try {
				HtmlToPdf.convert("src\\main\\java\\com\\project1\\template.html",
						"src\\main\\resources\\bill.pdf");
				JOptionPane.showMessageDialog(this, "In Hoa Don Thanh Cong (src\\main\\resources\\test.pdf)");
			} catch (DocumentException | IOException e) {
				e.printStackTrace();
			}
		});
		
		giamGiaCheck.addActionListener(event -> {
			boolean selected = giamGiaCheck.isSelected();
			giamGiaField.setEnabled(selected);
		});
		
		phieuMHCheck.addActionListener(event -> {
			boolean selected = phieuMHCheck.isSelected();
			phieuMHField.setEnabled(selected);
		});
		
		tienTheCheck.addActionListener(event -> {
			boolean selected = tienTheCheck.isSelected();
			tienTheField.setEnabled(selected);
		});
		
	}

	private void setProperties() {
		
		addressField.setText("193-195 Dương Văn Dương, P.Tân Quý, Q.Tân Bình, Tp.HCM");
		soCTField.setText("2108001160001439");
		dateTimeField.setText("01/08/2021 13:13");
		empField.setText("Khấu Minh Duy");
		
		giamGiaField.setEnabled(false);
		phieuMHField.setEnabled(false);
		tienTheField.setEnabled(false);
		
		Dimension dimension = getPreferredSize();
		dimension.width = 500;
		setPreferredSize(dimension);
		
		addressField.setWrapStyleWord(true);
		addressField.setLineWrap(true);
		
		Border innerBorder = BorderFactory.createTitledBorder("Infomation");
		Border outnerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outnerBorder, innerBorder));
		
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		
		// Row 1
		gc.gridy = 0;
		
		gc.gridx = 0;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(addressLabel, gc);
		
		gc.gridx = 1;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(addressField, gc);
		
		// Row 2
		
		gc.gridy++;
		
		gc.gridx = 0;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(soCTLabel, gc);
		
		gc.gridx = 1;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(soCTField, gc);
		
		// Row 3
		
		gc.gridy++;
		
		gc.gridx = 0;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(dateTimeLabel, gc);
		
		gc.gridx = 1;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(dateTimeField, gc);
		
		// Row 4
		
		gc.gridy++;
		
		gc.gridx = 0;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(empLabel, gc);
		
		gc.gridx = 1;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(empField, gc);
		
		// Row 5
		
		gc.gridy++;
		
		gc.gridx = 0;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 80, 0, 0);
		add(giamGia, gc);
		
		gc.gridx = 0;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(giamGiaCheck, gc);
		
		gc.gridx = 1;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(giamGiaField, gc);
		
		// Row 6
		
		gc.gridy++;
		
		gc.gridx = 0;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 80, 0, 0);
		add(phieuMuaHang, gc);
		
		gc.gridx = 0;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(phieuMHCheck, gc);
		
		gc.gridx = 1;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(phieuMHField, gc);
		
		// Row 7
		
		gc.gridy++;
		
		gc.gridx = 0;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 80, 0, 0);
		add(tienThe, gc);
		
		gc.gridx = 0;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(tienTheCheck, gc);
		
		gc.gridx = 1;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(tienTheField, gc);
		
		// Row 8
		
		gc.gridy++;
		
		gc.gridx = 0;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(luuBtn, gc);
		
		gc.gridx = 1;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 5, 0, 0);
		add(inBtn, gc);
		
	}

	public void addFormPerforment(FormListener formListener) {
		this.formListener = formListener;
	}
	
}
