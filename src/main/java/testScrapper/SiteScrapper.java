package testScrapper;

import APIs.Chrome;

public class SiteScrapper {

	public static void main(String[] args) {
		Chrome chrome=Chrome.getChromeInstance();
		chrome.get("https://personal.vanguard.com/us/hnwnesc/PMLogin");
		String methods=chrome.getAllScripts();
		System.out.println("method list:"+methods);
		chrome.getDriver().close();
	}
}




