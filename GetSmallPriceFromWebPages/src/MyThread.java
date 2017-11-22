import java.io.IOException;
import java.util.concurrent.locks.ReentrantLock;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class MyThread implements Runnable {
	private ReentrantLock filter;
	private HeadPhonesRepository headPhones;
	private String cellUrl;
	private String emagUrl;

	public MyThread(HeadPhonesRepository headPhones, String cellUrl, String emagUrl, ReentrantLock theFilter) {
		super();
		this.filter = theFilter;
		this.headPhones = headPhones;
		this.cellUrl = cellUrl;
		this.emagUrl = emagUrl;

	}

	@Override
	public void run() {

		if (!Application.cellNoMoreWebPage) {
			parseCel(cellUrl);
		}
		if (!Application.emagNoMorePage) {
			parseEmag(emagUrl);
		}

	}

	public void parseCel(String theUrl) {

		System.out.println(" Cell url" + theUrl);
		if (theUrl.equals("http://www.cel.ro/casti/0a-25")) {
			Application.cellNoMoreWebPage = true;
		}
		Document doc = null;
		try {
			doc = Jsoup.connect(theUrl).userAgent("Mozilla").get();
			if (doc == null) {
				return;
			}

			org.jsoup.select.Elements prices = doc.select("b[itemprop = price]");
			org.jsoup.select.Elements ids = doc.select("div.stoc_list").select("span[id]");
			org.jsoup.select.Elements urls = doc.select("h4.productTitle").select("a[href]");
			org.jsoup.select.Elements titles = doc.select("h4.productTitle").select("span[itemprop]");

			Float[] allPrice = new Float[prices.size()];
			String[] allId = new String[ids.size()];
			String[] allURL = new String[urls.size()];
			String[] allTitle = new String[titles.size()];
			int contor = 0;
			for (Element price : prices) {
				allPrice[contor] = Float.parseFloat(price.attr("content"));
				contor++;

			}
			contor = 0;
			for (Element id : ids) {
				String i = substringForCellToValidateId(id.attr("id"), id.attr("id").length() - 2);
				allId[contor] = i;
				contor++;
			}
			contor = 0;
			for (Element url : urls) {
				allURL[contor] = url.attr("abs:href");
				contor++;
			}
			contor = 0;
			for (Element title : titles) {
				allTitle[contor] = title.text();
				contor++;
			}

			System.out.println("Number of products :" + contor);
			filter.lock();
			try {
				for (int i = 0; i < contor; i++) {
					/*
					 * System.out.println("Price : " + allPrice[i]); System.out.println("Id : " +
					 * allId[i]); System.out.println("URL : " + allURL[i]);
					 * System.out.println("Title : " + allTitle[i]);
					 */

					headPhones.addHeadPhones(allTitle[i], allId[i], allPrice[i], allURL[i]);

				}
			} finally {
				filter.unlock();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void parseEmag(String theUrl) {
		System.out.println("Emag url" + theUrl);
		if (theUrl.equals("https://www.emag.ro/casti-pc/p11/c")) {
			Application.emagNoMorePage = true;

		}
		Document doc = null;
		try {
			doc = Jsoup.connect(theUrl).userAgent("Mozilla").get();

			org.jsoup.select.Elements prices = doc.select("p.product-new-price");
			org.jsoup.select.Elements ids = doc.select("input[type = hidden]").select("[name = product[]]");
			org.jsoup.select.Elements urls = doc.select("h2.card-body.product-title-zone").select("a[href]");
			org.jsoup.select.Elements titles = doc.select("h2.card-body.product-title-zone");

			Float[] allPrice = new Float[prices.size()];
			String[] allId = new String[prices.size()];
			String[] allURL = new String[urls.size()];
			String[] allTitle = new String[titles.size()];
			int contor = 0;

			for (Element price : prices) {
				if (!(price.text().equals(""))) {
					String p = substringForEmagToFoundPrice(price.text(), price.text().length() - 4);
					if (p.contains("de la")) {
						p = substringForEmagToFoundPriceException(p, p.length());
					}
					Float pricefloat = 0.0f;
					try {
						pricefloat = Float.parseFloat(p);
						pricefloat /= (float) 100;

						allPrice[contor++] = pricefloat;
					} catch (NumberFormatException e) {
						e.printStackTrace();
					}
				}

			}
			contor = 0;
			for (Element id : ids) {
				allId[contor++] = id.attr("value");
			}
			contor = 0;
			for (Element url : urls) {
				allURL[contor++] = url.select("a").attr("abs:href");
			}
			contor = 0;
			for (Element title2 : titles) {

				allTitle[contor++] = title2.text();
			}
			System.out.println("Number of products :" + contor);
			filter.lock();
			try {
				for (int i = 0; i < contor; i++) {
					/*
					 * System.out.println("Price : " + allPrice[i]); System.out.println("Id : " +
					 * allId[i]); System.out.println("URL : " + allURL[i]);
					 * System.out.println("Title : " + allTitle[i]);
					 */

					headPhones.addHeadPhones(allTitle[i], allId[i], allPrice[i], allURL[i]);

				}
			} finally {
				filter.unlock();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String substringForEmagToFoundPrice(String s, int width) {
		String newString = "";
		for (int i = 0; i < width; i++) {
			if (s.charAt(i) != '.') {
				newString = newString + s.charAt(i);
			}
		}
		return newString;
	}

	private String substringForEmagToFoundPriceException(String s, int width) {
		String newString = "";
		for (int i = 5; i < width; i++) {
			newString = newString + s.charAt(i);
		}
		return newString;
	}

	private String substringForCellToValidateId(String s, int width) {
		String newString = "";
		for (int i = 0; i < width; i++) {
			if ((s.charAt(i) != 's')) {
				newString = newString + s.charAt(i);
			}
		}
		return newString;
	}

}
