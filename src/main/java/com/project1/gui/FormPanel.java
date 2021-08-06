package com.project1.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
	private JLabel quantityLabel;
	private JLabel productNameLabel;
	private JLabel salePriceLabel;
	private JLabel productNameListLabel;
	
	private JTextArea storeAddressField;
	private JTextField outputVoucherIdField;
	private JTextField outputDateField;
	private JTextField outputUserField;
	private JTextField totalDiscountField;
	private JTextField totalGiftVoucherField;
	private JTextField moneyCardField;
	private JTextField quantityField;
	private JTextField productNameField;
	private JTextField salePriceField;
	
	private JButton luuBtn;
	private JButton inBtn;
	
	private JCheckBox totalDiscountCheck;
	private JCheckBox totalGiftVoucherCheck;
	private JCheckBox moneyCardCheck;
	
	private JList<String> productNameList;
	DefaultListModel<String> model;
	
	private List<Product> products;
	
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
		quantityLabel = new JLabel("Số Lượng: ");
		productNameLabel = new JLabel("Tên Sản Phẩm: ");
		salePriceLabel = new JLabel("Giá Bán: ");
		productNameListLabel = new JLabel("Danh Sách Sản Phẩm: ");
		
		storeAddressField = new JTextArea(3, 20);
		outputVoucherIdField = new JTextField(20);
		outputDateField = new JTextField(20);
		outputUserField = new JTextField(20);
		totalDiscountField = new JTextField(20);
		totalGiftVoucherField = new JTextField(20);
		moneyCardField = new JTextField(20);
		quantityField = new JTextField(20);
		productNameField = new JTextField(20);
		salePriceField = new JTextField(20);
		
		luuBtn = new JButton("Luu");
		inBtn = new JButton("IN");
		
		totalDiscountCheck = new JCheckBox();
		totalGiftVoucherCheck = new JCheckBox();
		moneyCardCheck = new JCheckBox();
		
		productNameList = new JList<>();
		model = new DefaultListModel<>();
		productNameList.setModel(model);
		productNameList.setPreferredSize(new Dimension(200, 60));
		productNameList.setBorder(BorderFactory.createEtchedBorder());
		productNameList.setSelectedIndex(0);
		
		products = new ArrayList<>();
		
	}

	private void setEvent() {
		luuBtn.addActionListener(event -> {
			
			String storeAddress = storeAddressField.getText();
			String outputVoucherId = outputVoucherIdField.getText();
			String outputDate = outputDateField.getText();
			String outputUser = outputUserField.getText();
			String totalDiscount = "";
			String giftdiscount = "";
			String moneyCard = "";
			
			if(totalDiscountCheck.isSelected()) {
				totalDiscount = totalDiscountField.getText();
			}
			if(totalGiftVoucherCheck.isSelected()) {
				giftdiscount = totalGiftVoucherField.getText();
			}
			if(moneyCardCheck.isSelected()) {
				moneyCard = moneyCardField.getText();
			}
			
			String productName = productNameField.getText();
			String quantity = quantityField.getText();
			String salePrice = salePriceField.getText();
			if(!productName.equals("")) {
				model.addElement(productName);
				products.add(new Product(productName, processNumberFormatException(quantity), processNumberFormatException(salePrice)));
			}
			
			Bill bill = new Bill(
					storeAddress, 
					outputVoucherId, 
					outputDate, 
					outputUser,
					0, // totalamount
					processNumberFormatException(totalDiscount), 
					processNumberFormatException(giftdiscount),
					processNumberFormatException(moneyCard), 
					"", // barcode base64
					""); // qrcode base64
			bill.setProducts(products);
			
			
			
			FormEvent formEvent = new FormEvent(this, bill);
			if(formListener != null) {
				formListener.formPerforment(formEvent);
			}
			productNameField.setText("");
			quantityField.setText("");
			salePriceField.setText("");
			
			
		});
		
		inBtn.addActionListener(event -> {
			try {
				HtmlToPdf.convert("src\\main\\resources\\pdf.html",
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
		outputDateField.setText("05/08/2021 13:13");
		outputUserField.setText("Khấu Minh Duy");
		productNameField.setText("Dưa Leo");
		quantityField.setText("10");
		salePriceField.setText("10000");
		
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
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(productNameLabel, gc);
		
		gc.gridx = 1;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(productNameField, gc);
		
		// Row 6
		gc.gridy++;
		gc.gridx = 0;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(quantityLabel, gc);
		
		gc.gridx = 1;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(quantityField, gc);
		
		// Row 7
		gc.gridy++;
		gc.gridx = 0;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(salePriceLabel, gc);
		
		gc.gridx = 1;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(salePriceField, gc);
		
		// Row 8
		gc.gridy++;
		gc.gridx = 0;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(productNameListLabel, gc);
		
		gc.gridx = 1;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(new JScrollPane(productNameList), gc);
		
		// Row 9
		gc.gridy++;
		gc.gridx = 0;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 20);
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
		
		// Row 10
		gc.gridy++;
		gc.gridx = 0;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 20);
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
		
		// Row 11
		gc.gridy++;
		gc.gridx = 0;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 20);
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
		
		// Row 12
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
	
	private int processNumberFormatException(String input) {
		try {
			return Integer.parseInt(input);
		} catch(NumberFormatException e) {
			return 0;
		}
	}
	
}
