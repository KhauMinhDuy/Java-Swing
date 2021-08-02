package com.khauminhduy.util;

public class Utils {

	public static String getFileExtensions(String fileName) {
		int dotIndex = fileName.lastIndexOf(".");
		if (dotIndex == -1) {
			return null;
		}
		if (dotIndex == fileName.length() - 1) {
			return null;
		}
		return fileName.substring(dotIndex + 1, fileName.length());

	}
}
