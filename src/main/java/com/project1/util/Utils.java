package com.project1.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;

public class Utils {

	public static String replaceParams(String src, String name, String value, String openTag, String closeTag) {
		String pattern = openTag + name + closeTag;
		return src.replaceAll(pattern, value);
	}

	public static String replaceParams(String src, String name, String value) {
		return replaceParams(src, name, value, "\\$\\{", "\\}");
	}

	public static String generateBarcode128(String barcodeText) throws Exception {
		Barcode barcode = BarcodeFactory.createCode128(barcodeText);
		barcode.setDrawingText(false);
		BufferedImage image = BarcodeImageHandler.getImage(barcode);
		final ByteArrayOutputStream os = new ByteArrayOutputStream();
		ImageIO.write(image, "png", os);
		return Base64.getEncoder().encodeToString(os.toByteArray());
	}

	public static String generateQRCodeImage(String barcodeText) throws Exception {
		QRCodeWriter barcodeWriter = new QRCodeWriter();
		BitMatrix bitMatrix = barcodeWriter.encode(barcodeText, BarcodeFormat.QR_CODE, 200, 200);
		BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
		final ByteArrayOutputStream os = new ByteArrayOutputStream();
		ImageIO.write(bufferedImage, "png", os);
		return Base64.getEncoder().encodeToString(os.toByteArray());
	}

	public static String encodeBase64(File file) throws IOException {
		byte[] readFileToByteArray = FileUtils.readFileToByteArray(file);
		return Base64.getEncoder().encodeToString(readFileToByteArray);
	}

}
