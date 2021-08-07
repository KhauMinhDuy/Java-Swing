package com.project1.util;

import java.util.HashMap;
import java.util.Map;

public class StringUtils {
	private static final Map<Character, String> htmlEncodeChars = new HashMap<>();

	static {

		// Special characters for HTML
		htmlEncodeChars.put('\u0026', "&amp;");
		htmlEncodeChars.put('\u003C', "&lt;");
		htmlEncodeChars.put('\u003E', "&gt;");
		htmlEncodeChars.put('\u0022', "&quot;");

	}

	private StringUtils() {
	}

	public static String encodeHtml(String source) {
		return encode(source, htmlEncodeChars);
	}

	private static String encode(String source, Map<Character, String> encodingTable) {
		if (null == source) {
			return null;
		}

		if (null == encodingTable) {
			return source;
		}

		StringBuffer encoded_string = null;
		char[] string_to_encode_array = source.toCharArray();
		int last_match = -1;
		int difference = 0;

		for (int i = 0; i < string_to_encode_array.length; i++) {
			char char_to_encode = string_to_encode_array[i];

			if (encodingTable.containsKey(char_to_encode)) {
				if (null == encoded_string) {
					encoded_string = new StringBuffer(source.length());
				}
				difference = i - (last_match + 1);
				if (difference > 0) {
					encoded_string.append(string_to_encode_array, last_match + 1, difference);
				}
				encoded_string.append(encodingTable.get(char_to_encode));
				last_match = i;
			}
		}

		if (null == encoded_string) {
			return source;
		} else {
			difference = string_to_encode_array.length - (last_match + 1);
			if (difference > 0) {
				encoded_string.append(string_to_encode_array, last_match + 1, difference);
			}
			return encoded_string.toString();
		}
	}
}
