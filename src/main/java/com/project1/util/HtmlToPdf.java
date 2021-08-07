package com.project1.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.text.DocumentException;

public class HtmlToPdf {

	public static void convert(String html, String pdf) throws DocumentException, IOException {
		File htmlSource = new File(html);
		File pdfDest = new File(pdf);
		
		ConverterProperties converterProperties = new ConverterProperties();
//		HtmlConverter.convertToPdf(new FileInputStream(htmlSource), new FileOutputStream(pdfDest), converterProperties);
		HtmlConverter.convertToPdf(html,  new FileOutputStream(pdfDest), converterProperties);
	}
}
