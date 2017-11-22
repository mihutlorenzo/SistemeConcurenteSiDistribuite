import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class HeadPhonesRepository {
	private List<HeadPhones> headPhones;

	public HeadPhonesRepository() {
		super();
		headPhones = new ArrayList<>();

	}

	public void addHeadPhones(String title, String id, Float price, String url) {
		headPhones.add(new HeadPhones(title, id, price, url));
	}

	public void getHeadsPhones() {
		
		for (HeadPhones headPhone : headPhones) {
			System.out.println("Titlul :" + headPhone.getTitle());
			System.out.println("Id - ul :" + headPhone.getId());
			System.out.println("Pretul :" + headPhone.getPrice());
			System.out.println("Url - ul:" + headPhone.getUrl());
		}

	}

	public void writeInFile() {
		FileOutputStream outputStream = null;

		try {
			outputStream = new FileOutputStream("simpleApp_out.txt");
			PrintWriter writer = new PrintWriter(outputStream);
			for (HeadPhones headPhone : headPhones) {
				writer.println(headPhone.getTitle());
				writer.println(headPhone.getId());
				writer.println(headPhone.getPrice());
				writer.println(headPhone.getUrl());
				writer.flush();
			}
		} catch (FileNotFoundException e) {
			System.out.println("Eroare la deschiderea fisierului " + e.getMessage());
		} catch (NoSuchElementException e) {
			System.out.println("Eroare la procesarea fisierului " + e.getMessage());

		} catch (Exception e) {
			System.out.println("Eroare la procesarea fisierului " + e.getMessage());

		}

		if (outputStream != null) {
			try {
				outputStream.close();
			} catch (IOException e) {
				System.out.println("Fisierul nu este deschis." + e.getMessage());
			}
		}
	}

}
