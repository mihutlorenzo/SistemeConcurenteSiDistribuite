import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SideVehicle implements Runnable {
	private Thread[] vehicles;
	private Direction sideDirection;
	private int counter;
	private final Semaphore semaphore;
	static volatile Direction direction;

	public SideVehicle(Direction dir, int numberOfVehicles, Bridge bridge) {
		semaphore = new Semaphore(1, true);
		sideDirection = dir;
		vehicles = new Thread[numberOfVehicles];
		for (int i = 0; i < numberOfVehicles; i++) {
			vehicles[i] = new Thread(new Car(bridge, i));
		}
		counter = 0;
	}

	@Override
	public  void run() {
		
		while(true) {
			if(counter % 2 == 0) {
				direction= Direction.LEFT;
			}
			else if(counter%2 == 1) {
				direction = Direction.RIGHT;
			}
			try {
				while (sideDirection != direction) {
					semaphore.acquire();
				}
				vehicles[counter % vehicles.length].start();
				counter++;
				System.out.println();
				System.out.println("Vehiculul care vine din directia " + direction + " cu numarul " + (counter - 1));
				System.out.println();
				TimeUnit.SECONDS.sleep((long)(Math.random()*10));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				semaphore.release();
			}
		}

	}

}
