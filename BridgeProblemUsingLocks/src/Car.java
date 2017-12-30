
import java.util.Random;

public class Car implements Runnable {
	private int number;
	private int travels;
	private Bridge bridge;
	private Direction direction;

	public Car(int number, Bridge monitor, int travels) {
		this.number = number;
		this.bridge = monitor;
		this.travels = travels;

		Random rand = new Random();
		if (rand.nextInt(101) <= 50)
			direction = Direction.NORTH;
		else
			direction = Direction.SOUTH;
	}

	public void run() {
		int t = 0;
		while (t < travels) {
			try {
				travel_to_bridge();
				bridge.crossBridge(direction);
				++t;
				crossed();
				distantiate_from_bridge(t);
				change_direction();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		OneLaneBridge.set_completed((OneLaneBridge.get_completed()) + 1);
		if ((OneLaneBridge.get_completed()) == OneLaneBridge.get_all_cars())
			OneLaneBridge.set_finish(true);

		System.out.println("Car " + number + " has arrived at home!");
	}

	private void distantiate_from_bridge(int t) {
		if (t < travels - 1) {
			if (direction == direction.SOUTH)
				System.out
						.println("Car " + number + " has finished his business at North. He must now return to South.");
			else
				System.out
						.println("Car " + number + " has finished his business at South. He must now return to North.");
		} else
			System.out.println("Car " + number + " has finished his business for today. He is now returning home.");
	}

	private void crossed() throws InterruptedException {

		System.out.println(
				"Car " + number + " has crossed the bridge. He will now do his business at " + direction + " .");
		Thread.sleep(10000);
	}

	/*
	 * private void arrive() { if (direction == 'N') System.out.println("Car " +
	 * number +
	 * " has arrived at the bridge. He wants to cross it to get to North."); else
	 * System.out.println("Car " + number +
	 * " has arrived at the bridge. He wants to cross it to get to South."); }
	 */

	private void change_direction() {
		if (direction == Direction.NORTH)
			direction = Direction.SOUTH;
		else
			direction = Direction.NORTH;
	}

	private void travel_to_bridge() throws InterruptedException {
		System.out.println("Car with number " + number + " is near  the bridge. He is going " + direction + " .");
		Random rand = new Random();
		Thread.sleep(rand.nextInt((5000) + 1) + 1000);
		System.out.println("Car with  " + number + " is waiting at the bridge. It cross to North " + direction + " .");
	}

}