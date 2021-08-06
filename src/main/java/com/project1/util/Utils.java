package com.project1.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;
import net.sourceforge.barbecue.output.OutputException;

public class Utils {

	public static String replaceParams(String src, String name, String value, String openTag, String closeTag) {
		String pattern = openTag + name + closeTag;
		return src.replaceAll(pattern, value);
	}

	public static String replaceParams(String src, String name, String value) {
		return replaceParams(src, name, value, "\\$\\{", "\\}");
	}

	public static String generateBarcode128(String barcodeText) throws BarcodeException, OutputException, IOException {
		Barcode barcode = BarcodeFactory.createCode128(barcodeText);
		barcode.setDrawingText(false);
		BufferedImage image = BarcodeImageHandler.getImage(barcode);
		final ByteArrayOutputStream os = new ByteArrayOutputStream();
		ImageIO.write(image, "png", os);
		return Base64.getEncoder().encodeToString(os.toByteArray());
	}

	public static String generateQRCodeImage(String barcodeText) throws IOException, WriterException {
		QRCodeWriter barcodeWriter = new QRCodeWriter();
		BitMatrix bitMatrix = barcodeWriter.encode(barcodeText, BarcodeFormat.QR_CODE, 200, 200);
		BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
		final ByteArrayOutputStream os = new ByteArrayOutputStream();
		ImageIO.write(bufferedImage, "png", os);
		return Base64.getEncoder().encodeToString(os.toByteArray());
	}

	public static String readFiletemplate(String string) throws IOException {
		List<String> allLines = Files.readAllLines(Paths.get(string));
		StringBuffer stringBuffer = new StringBuffer();
		for (String line : allLines) {
			stringBuffer.append(line);
		}
		return stringBuffer.toString();
	}

	public static void writeTemplate(String path, String html) throws IOException {
		Files.write(Paths.get(path), html.getBytes());
		
	}
	public static byte[] writeTemplate(String html) throws IOException {
		return html.getBytes();
	}
	
	public static void printHtmlPrint(String htmlData, String printName) throws Exception {
		try(ByteArrayOutputStream output = new ByteArrayOutputStream()) {
			Document document = new Document();
			PdfWriter pdfWriter = PdfWriter.getInstance(document, output);
			document.open();
			InputStream in = new ByteArrayInputStream(htmlData.getBytes(Charset.forName("UTF-8")));
			XMLWorkerHelper.getInstance().parseXHtml(pdfWriter, document, in);
			document.close();
			try(ByteArrayInputStream input = new ByteArrayInputStream(output.toByteArray())) {
				prinfPdf(input, printName, true, false);
			}
		}
	}

	private static void prinfPdf(ByteArrayInputStream input, String printName, boolean b, boolean c) {
		
	}

}
