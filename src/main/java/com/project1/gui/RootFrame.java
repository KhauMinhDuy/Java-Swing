package com.project1.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.text.Document;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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
		
		html = readFiletemplate(PATH_FILE);
		html  = replaceStr(html, new Bill());
		jEditorPane.setEditable(false);
		jEditorPane.setContentType("text/html");
		jEditorPane.setText(html);
		
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
	
	private static String formatCurrency(int money) {
		String s = String.valueOf(money);
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return formatter.format(Double.parseDouble(s));
    }
	
	private String replaceStr(String html, Bill bill) {
		org.jsoup.nodes.Document doc = Jsoup.parse(html);
		
		Elements select = doc.select("tr");
		for(Element e : select) {
			System.out.println(e);
			
		}
		
		
		Elements elements = doc.body().getElementsByTag("td");
		for(Element element : elements) {
			switch(element.text()) {
			case "COMPANYNAME":
				element.text(bill.getCompanyName());
				break;
			case "WEBSITE":
				element.text(bill.getWebsite());
				break;
			case "STOREADDRESS":
				element.text(bill.getStoreAddress());
				break;
			case "OUTPUTVOUCHERID":
				element.text(bill.getOutputVoucherID());
				break;
			case "OUTPUTDATE":
				element.text(bill.getOutputDATE());
				break;
			case "OUTPUTUSER":
				element.text(bill.getOutputUSER());
				break;

			case "TOTALAMOUNT":
				element.text(formatCurrency(bill.getTotalAmount()));
				break;
			case "TOTALDISCOUNT":
				if(bill.getTotalDiscount() != 0) {
					element.text(formatCurrency(bill.getTotalDiscount()));
				} else {
					element.parent().remove();
				}
				break;
			
			case "TOTALGIFTVOUCHERAMOUNT":
				if(bill.getTotalGiftVoucherAmount() != 0) {
					element.text(formatCurrency(bill.getTotalGiftVoucherAmount()));
				} else {
					element.parent().remove();
				}
				break;
			case "MONEYCARD":
				if(bill.getMoneyCard() != 0) {
					element.text(formatCurrency(bill.getMoneyCard()));
				} else {
					element.parent().remove();
				}
				break;
			case "TOTALAMOUNTROUND":
				element.text(formatCurrency(bill.getTotalAmountRound()));
				break;
			case "SPECIALMESSAGE":
				element.text(bill.getSpecialMessage());
				break;
			case "":
				element.childNodes().forEach(e -> {
					String attr = e.attr("src");
					if(attr.contains("IMAGEQRCODEBASE64")) {
						attr = attr.replace("IMAGEQRCODEBASE64", bill.getQrCode());
						e.attr("src", attr);
					} else if(attr.contains("IMAGEBARCODEBASE64")) {
						attr = attr.replace("IMAGEBARCODEBASE64", bill.getBarcode());
						e.attr("src", attr);
					}
				});
				break;
			
			}
			
		}
		return doc.toString();
	}
	
}
