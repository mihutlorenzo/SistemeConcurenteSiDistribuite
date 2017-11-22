
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {
	public static boolean status = false;
	public static double startNumber = 10000000;
	public static int finish = 99999999;

	public static void main(String[] args) {

		System.out.println("Give me your index.");
		Scanner scanner = new Scanner(System.in);
		int index = scanner.nextInt();
		scanner.close();
		List<Thread> threads = new ArrayList<>();
		double pass = (double) (finish - startNumber + 1) / index;
		System.out.println("Pass" + pass);
		for (int i = 0; startNumber <= finish; i++) {
			threads.add(new Thread(new MyThread((int) startNumber, (int) (startNumber + pass), i)));
			startNumber += pass;

		}
		for (Thread thread : threads) {
			System.out.println(thread.getName());
			thread.start();
		}
		for (Thread thread : threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Main finished");

	}

}
