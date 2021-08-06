package com.project1;

import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import com.project1.util.Utils;

public class TestJsoup {

	public static void main(String[] args) {
		try {
			String readFiletemplate = Utils.readFiletemplate("src\\main\\java\\com\\project1\\template.html");
			Document parse = Jsoup.parse(readFiletemplate);

			Elements elements = parse.body().getElementsByTag("td");
			for (Element element : elements) {
				if (element.text().equals("")) {
					List<Node> childNodes = element.childNodes();
					childNodes.forEach(e -> {
						String attr = e.attr("src");
						if(attr.contains("IMAGEBARCODEBASE64")) {
							System.out.println(attr);
							attr = attr.replace("IMAGEBARCODEBASE64", "asdf");
							System.out.println(attr);
						} else if (attr.contains("IMAGEQRCODEBASE64")) {
							System.out.println(attr);
							attr = attr.replace("IMAGEQRCODEBASE64", "123");
							System.out.println(attr);
						}
					});
				}
			}

			Utils.writeTemplate("src/main/resources/text.html", parse.toString());

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
