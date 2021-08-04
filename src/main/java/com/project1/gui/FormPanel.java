package com.project1.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import com.project1.model.Product;
import com.project1.util.HtmlToPdf;


public class FormPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private JLabel storeAddressLabel;
	private JLabel outputVoucherIdLabel;
	private JLabel outputDateLabel;
	private JLabel outputUserLabel;
	private JLabel totalDiscountLabel;
	private JLabel totalGiftVoucherLabel;
	private JLabel moneyCardLabel;
	
	private JTextArea storeAddressField;
	private JTextField outputVoucherIdField;
	private JTextField outputDateField;
	private JTextField outputUserField;
	private JTextField totalDiscountField;
	private JTextField totalGiftVoucherField;
	private JTextField moneyCardField;
	
	private JButton luuBtn;
	private JButton inBtn;
	
	private JCheckBox totalDiscountCheck;
	private JCheckBox totalGiftVoucherCheck;
	private JCheckBox moneyCardCheck;
	
	private FormListener formListener;
	
	public FormPanel() {
		setControl();
		setEvent();
		setProperties();
	}
	
	private void setControl() {
		storeAddressLabel = new JLabel("Địa Chỉ: ");
		outputVoucherIdLabel = new JLabel("Số CT: ");
		outputDateLabel = new JLabel("Ngày CT: ");
		outputUserLabel = new JLabel("Nhân Viên: ");
		totalDiscountLabel = new JLabel("Đã Giảm: ");
		totalGiftVoucherLabel = new JLabel("Phiếu Mua Hàng: ");
		moneyCardLabel = new JLabel("Tiền Cà Thẻ: ");
		
		storeAddressField = new JTextArea(3, 20);
		outputVoucherIdField = new JTextField(20);
		outputDateField = new JTextField(20);
		outputUserField = new JTextField(20);
		totalDiscountField = new JTextField(20);
		totalGiftVoucherField = new JTextField(20);
		moneyCardField = new JTextField(20);
		
		luuBtn = new JButton("Luu");
		inBtn = new JButton("IN");
		
		totalDiscountCheck = new JCheckBox();
		totalGiftVoucherCheck = new JCheckBox();
		moneyCardCheck = new JCheckBox();
		
	}

	private void setEvent() {
		luuBtn.addActionListener(event -> {
			
			String storeAddress = storeAddressField.getText();
			String outputVoucherId = outputVoucherIdField.getText();
			String outputDate = outputDateField.getText();
			String outputUser = outputUserField.getText();
			String totalDiscount = totalDiscountField.getText();
			String giftdiscount = totalGiftVoucherField.getText();
			String moneyCard = moneyCardField.getText();
			
			Bill bill = new Bill(storeAddress, outputVoucherId, outputDate, outputUser);
			bill.setTotalDiscount(processNumberFormat(totalDiscount));
			bill.setTotalGiftVoucherAmount(processNumberFormat(giftdiscount));
			bill.setMoneyCard(processNumberFormat(moneyCard));
			bill.setProducts(getProducts());
			
			FormEvent formEvent = new FormEvent(this, bill);
			if(formListener != null) {
				formListener.formPerforment(formEvent);
			}
			
			
			
		});
		
		inBtn.addActionListener(event -> {
			try {
				HtmlToPdf.convert("src\\main\\java\\com\\project1\\template.html",
						"src\\main\\resources\\bill.pdf");
				JOptionPane.showMessageDialog(this, "In Hoa Don Thanh Cong (src\\main\\resources\\bill.pdf)");
			} catch (DocumentException | IOException e) {
				e.printStackTrace();
			}
		});
		
		totalDiscountCheck.addActionListener(event -> {
			boolean selected = totalDiscountCheck.isSelected();
			totalDiscountField.setEnabled(selected);
		});
		
		totalGiftVoucherCheck.addActionListener(event -> {
			boolean selected = totalGiftVoucherCheck.isSelected();
			totalGiftVoucherField.setEnabled(selected);
		});
		
		moneyCardCheck.addActionListener(event -> {
			boolean selected = moneyCardCheck.isSelected();
			moneyCardField.setEnabled(selected);
		});
		
	}

	private void setProperties() {
		
		storeAddressField.setText("193-195 Dương Văn Dương, P.Tân Quý, Q.Tân Bình, Tp.HCM");
		outputVoucherIdField.setText("2108001160001439");
		outputDateField.setText("01/08/2021 13:13");
		outputUserField.setText("Khấu Minh Duy");
		
		totalDiscountField.setEnabled(false);
		totalGiftVoucherField.setEnabled(false);
		moneyCardField.setEnabled(false);
		
		Dimension dimension = getPreferredSize();
		dimension.width = 500;
		setPreferredSize(dimension);
		
		storeAddressField.setWrapStyleWord(true);
		storeAddressField.setLineWrap(true);
		
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
		add(storeAddressLabel, gc);
		
		gc.gridx = 1;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(storeAddressField, gc);
		
		// Row 2
		
		gc.gridy++;
		
		gc.gridx = 0;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(outputVoucherIdLabel, gc);
		
		gc.gridx = 1;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(outputVoucherIdField, gc);
		
		// Row 3
		
		gc.gridy++;
		
		gc.gridx = 0;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(outputDateLabel, gc);
		
		gc.gridx = 1;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(outputDateField, gc);
		
		// Row 4
		
		gc.gridy++;
		
		gc.gridx = 0;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(outputUserLabel, gc);
		
		gc.gridx = 1;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(outputUserField, gc);
		
		// Row 5
		
		gc.gridy++;
		
		gc.gridx = 0;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 80, 0, 0);
		add(totalDiscountLabel, gc);
		
		gc.gridx = 0;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(totalDiscountCheck, gc);
		
		gc.gridx = 1;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(totalDiscountField, gc);
		
		// Row 6
		
		gc.gridy++;
		
		gc.gridx = 0;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 80, 0, 0);
		add(totalGiftVoucherLabel, gc);
		
		gc.gridx = 0;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(totalGiftVoucherCheck, gc);
		
		gc.gridx = 1;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(totalGiftVoucherField, gc);
		
		// Row 7
		
		gc.gridy++;
		
		gc.gridx = 0;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 80, 0, 0);
		add(moneyCardLabel, gc);
		
		gc.gridx = 0;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(moneyCardCheck, gc);
		
		gc.gridx = 1;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(moneyCardField, gc);
		
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
	
	private Integer processNumberFormat(String input) {
		try {
			return Integer.parseInt(input);
		} catch(NumberFormatException e) {
			return 0;
		}
	}
	
	private List<Product> getProducts() {
		List<Product> products = new ArrayList<>();
		Product dualeo = new Product("Dua Leo", 10, 19216, 192157);
		Product bia333 = new Product("BIA 333 LON 330ML THUNG 24", 10, 225784, 2257843);
		Product migoi = new Product("MÌ HẢO HẢO TÔM CHUA CAY 75G", 1, 3900, 3900);
		
		products.add(dualeo);
		products.add(bia333);
		products.add(migoi);
		
		return products;
	}
	
}
