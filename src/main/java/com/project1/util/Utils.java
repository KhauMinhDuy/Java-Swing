package com.project1.util;

public class Utils {

	public static String replaceParams(String src, String name, String value, String openTag, String closeTag) {
		String pattern = openTag + name + closeTag;
		return src.replaceAll(pattern, value);
	}

	public static String replaceParams(String src, String name, String value) {
		return replaceParams(src, name, value, "\\$\\{", "\\}");
	}
	
}
