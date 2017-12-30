import java.util.ArrayList;
import java.util.Random;

public class OneLaneBridge {

	private static int completed;
	private static boolean finish;
	private static int all_cars;

	public static void main(String[] args) {
		int total_cars= 0 , travels = 0, i = 0;
		if (args.length > 0) {
		    try {
		        total_cars = Integer.parseInt(args[0]);
		    } catch (NumberFormatException e) {
		        System.err.println("Argument" + args[0] + " must be an integer.");
		        System.exit(1);
		    }
		    try {
		        travels = Integer.parseInt(args[1]);
		    } catch (NumberFormatException e) {
		        System.err.println("Argument" + args[1] + " must be an integer.");
		        System.exit(1);
		    }
		}
		
		ArrayList<Car> cars = new ArrayList<Car>();
		Chronometer ch;

		// if(!correct_number_of_arguments(args.length)) use();

		ch = initiate_cars(cars, total_cars , travels );
		
		Thread[] carsThread = new Thread[total_cars];
		Thread chThread = new Thread(ch);
		completed = 0;
		finish = false;
		all_cars = total_cars;

		// Create threads
		for (Car c : cars) {
			//(new Thread(c)).start();
			carsThread[i] = new Thread(c);
			i++;
		}
		chThread.start();
		for(int j = 0 ;j < i;j++) {
			carsThread[j].start();
		}
		
		for(int j = 0 ;j < i;j++) {
			try {
				carsThread[j].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			chThread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	private static Chronometer initiate_cars(ArrayList<Car> cars, int total_cars, int travels) {
		Direction direction = Direction.NORTH;

		Chronometer ch;

		Bridge m = new Bridge(direction);

		for (int i = 0; i < total_cars; i++) {
			Car c = new Car(i, m, travels);
			cars.add(c);
		}

		return (ch = new Chronometer(m));
	}

	public static int get_all_cars() {
		return all_cars;
	}

	public static int get_completed() {
		return completed;
	}

	public static boolean get_finish() {
		return finish;
	}

	public static void set_completed(int t) {
		completed = t;
	}

	public static void set_finish(boolean t) {
		finish = t;
	}
}
