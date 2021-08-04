package com.project1.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;
import java.net.URL;
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
import com.project1.App;
import com.project1.Template;

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
		
		html = Template.getTemplate("Điah Chỉ", "Số CT", " Ngày CT", "Nhân Viên", false, false, false);
		
	}
	

	private void setEvent() throws IOException{
		formPanel.addFormPerforment(event -> {
		
				String path = "src\\main\\java\\com\\project1\\template.html";
				
				String address = event.getAddress();
				String soCT = event.getSoCT();
				String dateTime = event.getDateTime();
				String employee = event.getEmployee();
				boolean giamGia = event.getGiamGia();
				boolean phieuMH = event.getPhieuMH();
				boolean tienThe = event.getTienThe();
				html = Template.getTemplate(address, soCT, dateTime, employee, 
						giamGia, phieuMH, tienThe);
				
//				String html = readFiletemplate(path);
				
//				String replaceAddress = Utils.replaceParams(html, "diachi", address);
//				String replaceSoCT = Utils.replaceParams(replaceAddress, "soCT", soCT);
//				String replaceDateTime = Utils.replaceParams(replaceSoCT, "ngayCT", dateTime);
//				String replaceEmp = Utils.replaceParams(replaceDateTime, "nhanvien", employee);
//				
				try {
					writeTemplate("src\\main\\java\\com\\project1\\template.html", html);
				} catch (IOException e) {
					e.printStackTrace();
				}
//				
//				TimeUnit.SECONDS.sleep(3);
				
				Document doc = jEditorPane.getDocument();
				doc.putProperty(Document.StreamDescriptionProperty, null);
				
				jEditorPane.setContentType("text/html");
				jEditorPane.setText(html);
		});
	}



	private void setProperties() throws DocumentException, IOException {
		
		displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.PAGE_AXIS));
		jEditorPane.setEditable(false);
		URL url = App.class.getResource("template.html");
		//			jEditorPane.setPage(url);
		jEditorPane.setContentType("text/html");
		jEditorPane.setText(html);
		
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
