package com.project1.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
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
import com.project1.model.Customer;
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
	private JLabel qrCodeLabel;
	private JLabel qrCodedf;
	private JLabel salePriceDiscountLabel;
	private JLabel customerNameLabel;
	private JLabel customerPhoneLabel;
	
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
	private JTextField qrCodeField;
	private JTextField salePriceDiscountField;
	private JTextField customerNameField;
	private JTextField customerPhoneField;
	
	private JButton luuBtn;
	private JButton inBtn;
	
	private JCheckBox totalDiscountCheck;
	private JCheckBox totalGiftVoucherCheck;
	private JCheckBox moneyCardCheck;
	
	private JList<String> productNameList;
	private DefaultListModel<String> modelList;
	
	private List<Product> productList;
	
	private FormListener formListener;
	
	private String html;
	
	public FormPanel() {
		setControl();
		setEvent();
		setProperties();
	}
	
	private void setControl() {
		storeAddressLabel = new JLabel("?????a Ch???: ");
		outputVoucherIdLabel = new JLabel("S??? CT: ");
		outputDateLabel = new JLabel("Ng??y CT: ");
		outputUserLabel = new JLabel("Nh??n Vi??n: ");
		totalDiscountLabel = new JLabel("???? Gi???m: ");
		totalGiftVoucherLabel = new JLabel("Phi???u Mua H??ng: ");
		moneyCardLabel = new JLabel("Ti???n C?? Th???: ");
		quantityLabel = new JLabel("S??? L?????ng: ");
		productNameLabel = new JLabel("T??n S???n Ph???m: ");
		salePriceLabel = new JLabel("Gi?? B??n: ");
		productNameListLabel = new JLabel("Danh S??ch S???n Ph???m: ");
		qrCodeLabel = new JLabel("Ma QR: ");
		qrCodedf = new JLabel("SOCT_SOOL_");
		salePriceDiscountLabel = new JLabel("G??a B??n ???? gi???m");
		customerNameLabel = new JLabel("T??n Kh??ch H??ng: ");
		customerPhoneLabel = new JLabel("S??? ??i???n Tho???i: ");
		
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
		qrCodeField = new JTextField(10);
		salePriceDiscountField = new JTextField(20);
		customerNameField = new JTextField(20);
		customerPhoneField = new JTextField(20);
		
		luuBtn = new JButton("Luu");
		inBtn = new JButton("IN");
		
		totalDiscountCheck = new JCheckBox();
		totalGiftVoucherCheck = new JCheckBox();
		moneyCardCheck = new JCheckBox();
		
		productNameList = new JList<>();
		modelList = new DefaultListModel<>();
		productNameList.setModel(modelList);
		productNameList.setPreferredSize(new Dimension(200, 60));
		productNameList.setBorder(BorderFactory.createEtchedBorder());
		productNameList.setSelectedIndex(0);
		
		productList = new ArrayList<>();
		
	}

	private void setEvent() {
		luuBtn.addActionListener(event -> {
			
			String storeAddress = storeAddressField.getText();
			String outputVoucherId = outputVoucherIdField.getText();
			String outputDate = outputDateField.getText();
			String outputUser = outputUserField.getText();
			String customerName = customerNameField.getText();
			String customerPhone = customerPhoneField.getText();
			String barcode = outputVoucherId;
			String qrcode = outputVoucherIdField.getText()+ "_SOOL_" + qrCodeField.getText();
			
			String totalDiscountStr = "";
			if(totalDiscountCheck.isSelected()) {
				totalDiscountStr = totalDiscountField.getText();
			}
			String giftdiscountStr = "";
			if(totalGiftVoucherCheck.isSelected()) {
				giftdiscountStr = totalGiftVoucherField.getText();
			}
			String moneyCardStr = "";
			if(moneyCardCheck.isSelected()) {
				moneyCardStr = moneyCardField.getText();
			}
			String productName = productNameField.getText();
			String quantityStr = quantityField.getText();
			String salePriceStr = salePriceField.getText();
			String salePriceDiscountStr = salePriceDiscountField.getText();
			
			int quantity = processNumberFormatException(quantityStr);
			int salePrice = processNumberFormatException(salePriceStr);
			int salePriceDiscount = 0;
			if(!salePriceDiscountStr.equals("")) {
				salePriceDiscount = processNumberFormatException(salePriceDiscountStr);
			} else {
				salePriceDiscount = salePrice;
			}
			
			if(!productName.equals("") && quantity != 0 && salePrice != 0) {
				modelList.addElement(productName);
				productList.add(new Product(productName, quantity, salePrice, salePriceDiscount));
			}
			
			Bill bill = new Bill(
					storeAddress, 
					outputVoucherId, 
					outputDate, 
					outputUser,
					productList,
					0, // totalamount
					processNumberFormatException(totalDiscountStr), 
					processNumberFormatException(giftdiscountStr),
					processNumberFormatException(moneyCardStr), 
					barcode, 
					qrcode); 
			bill.setCustomer(new Customer(customerName, customerPhone));
			
			FormEvent formEvent = new FormEvent(this, bill);
			if(formListener != null) {
				formListener.formPerforment(formEvent);
			}
			
			productNameField.setText("");
			quantityField.setText("");
			salePriceField.setText("");
			salePriceDiscountField.setText("");

			
			
			
		});
		
		inBtn.addActionListener(event -> {
			try {
				HtmlToPdf.convert("src\\main\\resources\\pdf.html", "src\\main\\resources\\bill.pdf");
				JOptionPane.showMessageDialog(this, "Luu Thanh Cong");
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
		
		storeAddressField.setText("193-195 D????ng V??n D????ng, P.T??n Qu??, Q.T??n B??nh, Tp.HCM");
		outputVoucherIdField.setText("2108001160001439");
		outputDateField.setText("05/08/2021 13:13");
		outputUserField.setText("Kh???u Minh Duy");
		productNameField.setText("D??a Leo");
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
		add(customerNameLabel, gc);
		
		gc.gridx = 1;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(customerNameField, gc);
		
		// Row 6
		gc.gridy++;
		gc.gridx = 0;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(customerPhoneLabel, gc);
		
		gc.gridx = 1;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(customerPhoneField, gc);
		
		// Row 5
		gc.gridy++;
		gc.gridx = 0;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(qrCodeLabel, gc);
		
		gc.gridx = 1;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		JPanel jPanel = new JPanel(new FlowLayout());
		jPanel.add(qrCodedf);
		jPanel.add(qrCodeField);
		add(jPanel, gc);
		
	
		
		// Row 6
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
		
		// Row 7
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
		
		// Row 8
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
		
		// row 9
		gc.gridy++;
		gc.gridx = 0;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(salePriceDiscountLabel, gc);
		
		gc.gridx = 1;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(salePriceDiscountField, gc);
		
		// Row 10
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
		
		// Row 11
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
		
		// Row 12
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
		
		// Row 13
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
		
		// Row 14
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
