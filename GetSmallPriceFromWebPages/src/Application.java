import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.concurrent.locks.ReentrantLock;

public class Application {
	
	private static HeadPhonesRepository headPhones = new HeadPhonesRepository();
	private static int numberOfThreads = 101;
	private static String cellUrl = "http://www.cel.ro/casti/";
	private static String emagUrl = "https://www.emag.ro/casti-pc/c";
	public static boolean emagNoMorePage = false;
	public static boolean  cellNoMoreWebPage = false;

	public static void main(String[] args) {
		Thread[] threads = new Thread[numberOfThreads];
		ReentrantLock filter = new ReentrantLock();
		for(int i = 0;i< numberOfThreads;i++) {
			
			threads[i] = new Thread(new MyThread(headPhones, cellUrl, emagUrl,filter));
			cellUrl = nextCellPage(i + 2);
			emagUrl = nextEmagPage(i + 2);
		}
		
		for(int i = 0;i<numberOfThreads ; i++) {
			try {
				Thread.sleep(500);
				threads[i].start();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		for (int i = 0;i< numberOfThreads;i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Main finish");
		
	//	headPhones.getHeadsPhones();
		headPhones.writeInFile();
		
		
		
	}
	
	private static String nextCellPage(int index) {
		return "http://www.cel.ro/casti/0a-" + index;
	}
	
	private static String nextEmagPage(int index) {
		return "https://www.emag.ro/casti-pc/p" + index + "/c";
	}
	
	
	

}
