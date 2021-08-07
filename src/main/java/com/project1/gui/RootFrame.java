package com.project1.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;
import java.nio.charset.Charset;
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
import com.project1.model.Customer;
import com.project1.model.Product;
import com.project1.util.HtmlToPdf;
import com.project1.util.Utils;

public class RootFrame extends JFrame {

	private static final String PATH_FILE = "src\\main\\java\\com\\project1\\template.html";
	private static final String PATH_FILE_NO_QR = "src\\main\\java\\com\\project1\\template_no_qr.html";

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

	private void setEvent() {

		formPanel.addFormPerforment(event -> {
			try {

				Bill bill = new Bill(event.getStoreAddress(), 
						event.getOutputVoucherID(), 
						event.getOutputDATE(),
						event.getOutputUSER(), 
						event.getProducts(),
						event.getTotalAmount(),
						event.getTotalDiscount(),
						event.getTotalGiftVoucherAmount(),
						event.getMoneyCard(),
						Utils.generateBarcode128(event.getOutputVoucherID()),
						Utils.generateQRCodeImage(event.getQRCode()));
				bill.setCustomer(new Customer(event.getCustomerName(), event.getCustomerPhone()));

				html = readFiletemplate(PATH_FILE_NO_QR);
				html = replaceStr(html, bill);
//				writeTemplate("src\\main\\resources\\pdf.html", html);
				

				Document doc = jEditorPane.getDocument();
				doc.putProperty(Document.StreamDescriptionProperty, null);

				jEditorPane.setContentType("text/html");
				jEditorPane.setText(html);
				event.setHtml(html);
//				HtmlToPdf.convert(html, "src\\main\\resources\\bill.pdf");
			} catch (Exception e1) {
				e1.printStackTrace();
			}

		});
	}

	private void setProperties() throws IOException {

		displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.PAGE_AXIS));

		html = readFiletemplate(PATH_FILE_NO_QR);
		html = replaceStr(html, new Bill());
		jEditorPane.setEditable(false);
		jEditorPane.setContentType("text/html");
		jEditorPane.setText(html);

		displayPanel.add(new JScrollPane(jEditorPane));

		add(displayPanel, BorderLayout.CENTER);
		add(formPanel, BorderLayout.WEST);

		setMinimumSize(new Dimension(1100, 1000));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		pack();
		setVisible(true);

	}

	private String readFiletemplate(String string) throws IOException {
		List<String> allLines = Files.readAllLines(Paths.get(string));
		StringBuffer stringBuffer = new StringBuffer();
		for (String line : allLines) {
			stringBuffer.append(line);
		}
		return stringBuffer.toString();
	}

	private void writeTemplate(String path, String html) throws IOException {
		Files.write(Paths.get(path), html.getBytes());
	}

	public static byte[] writeTemplate(String html) throws IOException {
		return html.getBytes(Charset.forName("UTF-8"));
	}

	private static String formatCurrency(int money) {
		DecimalFormat formatter = new DecimalFormat("###,###,###");
		return formatter.format(Double.parseDouble(String.valueOf(money)));
	}

	private String replaceStr(String html, Bill bill) {
		org.jsoup.nodes.Document doc = Jsoup.parse(html);

		Element productName = doc.getElementsByClass("productname").first();
		Element productDetail = doc.getElementsByClass("productdetail").first();

		for (int i = 0; i < bill.getProducts().size(); i++) {

			Element proNameClone = productName.clone();
			Element proDetailClone = productDetail.clone();

			Product product = bill.getProducts().get(i);

			for (Element e : productName.children()) {
				if (e.text().equals("PRODUCTNAME")) {
					e.text(String.valueOf(product.getProductName()));
				}
			}

			for (Element detail : productDetail.children()) {

				switch (detail.text()) {
				case "QUANTITY":
					detail.text(formatCurrency(product.getQuantity()));
					break;
				case "TOTALAMOUNTVAT":
					detail.text(formatCurrency(product.getTotalAmountVAT()));
					break;
				default:
					Elements children = detail.children();
					for (Element span : children) {
						if (product.getSalePriceVAT() == product.getSalePriceAfterDiscount()) {
							switch (span.text()) {
							case "SALEPRICEVAT":
								span.text("");
								break;
							case "AFTERDISCOUNT":
								span.text(formatCurrency(product.getSalePriceAfterDiscount()));
								break;
							}
						} else {
							switch (span.text()) {
							case "SALEPRICEVAT":
								span.text(formatCurrency(product.getSalePriceVAT()));
								break;
							case "AFTERDISCOUNT":
								span.text(formatCurrency(product.getSalePriceAfterDiscount()));
								break;
							}
						}
					}
				}

			}

			if (i == bill.getProducts().size() - 1)
				break;
			productDetail.after(proNameClone);
			productName = proNameClone;
			productName.after(proDetailClone);
			productDetail = proDetailClone;
		}

		Elements elements = doc.body().getElementsByTag("td");

		for (Element td : elements) {
			switch (td.text()) {
			case "COMPANYNAME":
				td.text(bill.getCompanyName());
				break;
			case "WEBSITE":
				td.text(bill.getWebsite());
				break;
			case "STOREADDRESS":
				td.text(bill.getStoreAddress());
				break;
			case "OUTPUTVOUCHERID":
				td.text(bill.getOutputVoucherID());
				break;
			case "OUTPUTDATE":
				td.text(bill.getOutputDATE());
				break;
			case "OUTPUTUSER":
				td.text(bill.getOutputUSER());
				break;
			case "CUSTOMERNAME":
				td.parent().remove();
				break;
			case "CUSTOMERPHONE":
				td.parent().remove();
				break;
			case "TOTALAMOUNT":
				td.text(formatCurrency(bill.getTotalAmount()));
				break;
			case "TOTALDISCOUNT":
				if (bill.getTotalDiscount() != 0) {
					td.text(formatCurrency(bill.getTotalDiscount()));
				} else {
					td.parent().remove();
				}
				break;
			case "TOTALVOUCHERDISCOUNT":
				td.parent().remove();
				break;
			case "TOTALGIFTVOUCHERAMOUNT":
				if (bill.getTotalGiftVoucherAmount() != 0) {
					td.text(formatCurrency(bill.getTotalGiftVoucherAmount()));
				} else {
					td.parent().remove();
				}
				break;
			case "MONEYCARD":
				if (bill.getMoneyCard() != 0) {
					td.text(formatCurrency(bill.getMoneyCard()));
				} else {
					td.parent().remove();
				}
				break;
			case "CASHVND":
				td.parent().remove();
				break;
			case "TOTALAMOUNTROUND":
				td.text(formatCurrency(bill.getTotalAmountRound()));
				break;
			case "REFUNDMONEY":
				td.parent().remove();
				break;
			case "SPECIALMESSAGE":
				td.text(bill.getSpecialMessage());
				break;
			case "":
				td.childNodes().forEach(e -> {
					String attr = e.attr("src");
					if (attr.contains("IMAGEQRCODEBASE64")) {
						attr = attr.replace("IMAGEQRCODEBASE64", bill.getQrCode());
						e.attr("src", attr);
					} else if (attr.contains("IMAGEBARCODEBASE64")) {
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
