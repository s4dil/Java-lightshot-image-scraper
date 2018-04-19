package me.roman.lightshotdumper.utils;

import java.awt.Desktop;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Utils {

	private static List<String> links = new ArrayList<String>();

	public static String generateRandomLink(int len) {
		char[] letters = "abcdefghijklmnopqrstuvwxyz1234567890".toCharArray();
		String s = "";
		for (int i = 0; i < len; i++) {
			s += letters[new Random().nextInt(letters.length)];
			if (links.contains(s))
				s += letters[new Random().nextInt(letters.length)];

		}
		links.add(s);
		return "https://prnt.sc/" + s;
	}

	public static String checkLink(String URL) {
		String status = null;
		try {
			Document doc = Jsoup.connect(URL).get();

			Element link = doc.getElementById("screenshot-image");

			if (link.attr("src").contains("0_173a7b_211be8ff.png")) {
				System.out.println(URL + " > NOT VALID!");
				status = "NOT VALID!";
			} else {
				System.out.println(URL + " > VALID IMAGE!");
				status = "VALID IMAGE!";
			}
		} catch (IOException e) {
		}
		return URL + " | " + status;
	}

	public static void openWebpage(String urlString) {
		try {
			Desktop.getDesktop().browse(new URL(urlString).toURI());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
