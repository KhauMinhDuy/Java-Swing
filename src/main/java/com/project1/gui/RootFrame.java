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
import com.project1.model.Bill;
import com.project1.model.Product;
import com.project1.util.Utils;

public class RootFrame extends JFrame{

	private static final String PATH_FILE = "src\\main\\java\\com\\project1\\template.html";

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

	private void setControl() {
		setLayout(new BorderLayout());
		displayPanel = new JPanel();
		jEditorPane = new JEditorPane();
		formPanel = new FormPanel();
	}
	
	private void setEvent(){
		
		formPanel.addFormPerforment(event -> {
			try {
				List<Product> products = event.getProducts();
				Bill bill = new Bill(
						event.getStoreAddress(), 
						event.getOutputVoucherID(), 
						event.getOutputDATE(), 
						event.getOutputUSER(),
						event.getTotalAmount(), 
						event.getTotalDiscount(), 
						event.getTotalGiftVoucherAmount(), 
						event.getMoneyCard(),
						Utils.generateBarcode128(event.getOutputVoucherID()),
						Utils.generateQRCodeImage(event.getOutputVoucherID()+"_SOOL_"+45748660));
				bill.setProducts(products);
				
				html = readFiletemplate(PATH_FILE);
				html = replaceStr(html, bill);
				
				writeTemplate("src\\main\\resources\\pdf.html", html);
			} catch (Exception e1) {
				e1.printStackTrace();
			}

			Document doc = jEditorPane.getDocument();
			doc.putProperty(Document.StreamDescriptionProperty, null);

			jEditorPane.setContentType("text/html");
			jEditorPane.setText(html);
		});
	}

	private void setProperties() throws IOException {
		
		displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.PAGE_AXIS));
		
		String readFiletemplate = readFiletemplate(PATH_FILE);
		readFiletemplate = readFiletemplate.replace("COMPANYNAME", "BACH HOA XANH");
		readFiletemplate = readFiletemplate.replace("WEBSITE", "www.bachhoaxanh");
		
		jEditorPane.setEditable(false);
		jEditorPane.setContentType("text/html");
		jEditorPane.setText(readFiletemplate);
		
		displayPanel.add(new JScrollPane(jEditorPane));
		
		add(displayPanel, BorderLayout.CENTER);
		add(formPanel, BorderLayout.WEST);
		
		setMinimumSize(new Dimension(1200, 1000));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		pack();
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
	
	private void writeTemplate(String path, String html) throws IOException {
		Files.write(Paths.get(path), html.getBytes());
	}
	
	private String replaceStr(String html, Bill bill) {
		return html.replace("COMPANYNAME", bill.getCompanyName())
				.replace("WEBSITE", bill.getWebsite())
				.replace("STOREADDRESS", bill.getStoreAddress())
				.replace("OUTPUTVOUCHERID", bill.getOutputVoucherID())
				.replace("OUTPUTDATE", bill.getOutputDATE())
				.replace("OUTPUTUSER", bill.getOutputUSER())
				.replaceFirst("TOTALAMOUNT", String.valueOf(bill.getTotalAmount()))
				.replace("TOTALDISCOUNT", String.valueOf(bill.getTotalDiscount()))
				.replace("TOTALGIFTVOUCHERAMOUNT", String.valueOf(bill.getTotalGiftVoucherAmount()))
				.replace("MONEYCARD", String.valueOf(bill.getMoneyCard()))
				.replace("TOTALAMOUNTROUND", String.valueOf(bill.getTotalAmountRound()))
				.replace("IMAGEBARCODEBASE64", bill.getBarcode())
				.replace("IMAGEQRCODEBASE64", bill.getQrCode())
				.replace("SPECIALMESSAGE", bill.getSpecialMessage());
	}
	
}
