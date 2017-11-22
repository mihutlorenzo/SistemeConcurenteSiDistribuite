
public class HeadPhones {
	private String title;
	private String id;
	private float price;
	private String url;

	public HeadPhones(String title, String id, Float price, String url) {
		super();
		this.title = title;
		this.id = id;
		this.price = price;
		this.url = url;

	}

	public String getTitle() {

		return title;
	}

	public String getId() {

		return id;
	}

	public float getPrice() {
		return price;

	}

	public String getUrl() {
		return url;
	}
}
