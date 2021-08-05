package com.khauminhduy.test;

import java.awt.image.BufferedImage;
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

public class BarcodeDemo {

	public static void main(String[] args) throws IOException {
		try {
			BufferedImage generateBarcode128 = generateBarcode128("123123123");
			BufferedImage generateQRCodeImage = generateQRCodeImage("123123123123123123");
			File file = new File("src\\main\\resources\\image2.png");
			File file_qr = new File("src\\main\\resources\\image_qrcode.png");
			ImageIO.write(generateBarcode128, "png", file);
			ImageIO.write(generateQRCodeImage, "png", file_qr);
			String encodeBase64 = encodeBase64(file);
			String encodeBase642 = encodeBase64(file_qr);
			System.out.println(encodeBase64);
			System.out.println(encodeBase642);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static BufferedImage generateBarcode128(String barcodeText) throws Exception {
		Barcode barcode = BarcodeFactory.createCode128(barcodeText);
		barcode.setDrawingText(false);
		barcode.setAlignmentX(240);
		return BarcodeImageHandler.getImage(barcode);
	}

	public static String encodeBase64(File file) throws IOException {
		byte[] readFileToByteArray = FileUtils.readFileToByteArray(file);
		return Base64.getEncoder().encodeToString(readFileToByteArray);
	}

	public static BufferedImage generateQRCodeImage(String barcodeText) throws Exception {
		QRCodeWriter barcodeWriter = new QRCodeWriter();
		BitMatrix bitMatrix = barcodeWriter.encode(barcodeText, BarcodeFormat.QR_CODE, 200, 200);

		return MatrixToImageWriter.toBufferedImage(bitMatrix);
	}

}
