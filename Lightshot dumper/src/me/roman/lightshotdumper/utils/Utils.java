package me.roman.lightshotdumper.utils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Random;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Utils {

	public static String generateRandomLink(int len) {
		char[] letters = "abcdefghijklmnopqrstuvwxyz1234567890".toCharArray();
		String s = "";
		for (int i = 0; i < len; i++) {
			s += letters[new Random().nextInt(letters.length)];
		}
		return "https://prnt.sc/" + s;
	}

	public static String checkLink(String URL) throws MalformedURLException, IOException {
		Document doc = Jsoup.connect(URL).get();
		Element link = doc.getElementById("screenshot-image");
		
		String status = null;
		if (link.attr("src").contains("0_173a7b_211be8ff.png")) {
			System.out.println("NOT VALID!");
			status = "NOT VALID!";
		} else {
			System.out.println("VALID IMAGE!");
			status = "VALID IMAGE!";
		}
		return URL + " | " + status;
	}

}
