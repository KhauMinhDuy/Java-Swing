package com.project1.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.text.Document;

import com.itextpdf.text.DocumentException;
import com.project1.Template;
import com.project1.model.Bill;
import com.project1.model.Product;

public class RootFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private JPanel displayPanel;
	private JEditorPane jEditorPane;
	private FormPanel formPanel;
	
	private String html;
	
	public RootFrame() throws DocumentException, IOException {
		super("Bill");
		setControl();
		setEvent();
		setProperties();
	}

	private void setControl() throws DocumentException, IOException {
		setLayout(new BorderLayout());
		
		displayPanel = new JPanel();
		jEditorPane = new JEditorPane();
		formPanel = new FormPanel();
		
		
	}
	

	private void setEvent(){
		formPanel.addFormPerforment(event -> {
				String address = event.getStoreAddress();
				String soCT = event.getOutputVoucherID();
				String dateTime = event.getOutputDATE();
				String employee = event.getOutputUSER();
				Integer totalDiscount = event.getTotalDiscount();
				Integer totalGiftVoucherAmount = event.getTotalGiftVoucherAmount();
				Integer moneyCard = event.getMoneyCard();
				List<Product> products = event.getProducts();
				
				
				Bill bill = new Bill(address, soCT, dateTime, employee);
				bill.setTotalAmount(1000);
				bill.setTotalDiscount(totalDiscount);
				bill.setTotalGiftVoucherAmount(totalGiftVoucherAmount);
				bill.setMoneyCard(moneyCard);
				bill.setProducts(products);
				bill.setQrCode("src/main/resources/qr_code.jpg");
				bill.setBarcode("src/main/resources/barcode.png");
				System.out.println(bill.getTotalAmount());
				System.out.println(bill.getTotalDiscount());
				System.out.println(bill.getTotalGiftVoucherAmount());
				System.out.println(bill.getMoneyCard());
				System.out.println(bill.getTotalAmountRound());
				
				
				html = Template.getTemplate(bill);
				try {
					writeTemplate("src\\main\\java\\com\\project1\\template.html", html);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				
				Document doc = jEditorPane.getDocument();
				doc.putProperty(Document.StreamDescriptionProperty, null);
				
				jEditorPane.setContentType("text/html");
				jEditorPane.setText(html);
				
				
		});
	}

	private void setProperties() throws DocumentException, IOException {
		
		displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.PAGE_AXIS));
		jEditorPane.setEditable(false);

		jEditorPane.setContentType("text/html");
		Bill bill = new Bill();
		jEditorPane.setText(Template.getTemplate(bill));
		
		displayPanel.add(new JScrollPane(jEditorPane));
		
		add(displayPanel, BorderLayout.CENTER);
		add(formPanel, BorderLayout.WEST);
		
		setMinimumSize(new Dimension(1200, 1000));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	
	private String readFiletemplate(String string) throws IOException {
		List<String> allLines = Files.readAllLines(Paths.get(string));
		StringBuffer stringBuffer = new StringBuffer();
		for(String line : allLines) {
			stringBuffer.append(line);
		}
		return stringBuffer.toString();
	}
	
	private void writeTemplate(String string, String html) throws IOException {
		Files.write(Paths.get(string), html.getBytes());
	}
	
}
