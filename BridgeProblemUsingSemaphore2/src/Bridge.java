import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * This class describe the one line bridge that is accessed by the car from both side  
 * @author Lorenzo
 * @version 1.0
 *
 */

public class Bridge {
	private Direction direction;
	private final Lock lock;
	private final Condition north;
	private final Condition south;
	private final Semaphore lockSemaphore;
	private final Semaphore changeDirection;
	private final Semaphore buffer;
	
	
	public Bridge(Direction direction) {
		this.direction = direction;

		lock = new ReentrantLock(true);
		north = lock.newCondition();
		south = lock.newCondition();
		lockSemaphore = new Semaphore(1,true);
		changeDirection = new Semaphore(1,true);
		buffer = new Semaphore(5,true);

	}

	public void crossBridge(Car car) throws InterruptedException {
		Direction carDirection = car.getDirection();
		
		
		while(carDirection != direction) {
			changeDirection.acquire();
		}
		enterTheBridge(car);
		exitTheBridge(car);
		
		
		

	}

	private void exitTheBridge(Car car) {
		buffer.release();
		System.out.println("The car " + car.getNumber() + " exit on the bridge and is going to " +car.getDirection() );
		
	}

	private void enterTheBridge(Car car) {
		try {
			buffer.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		System.out.println("The car " + car.getNumber() + " is on the bridge and is going to " +car.getDirection() );
	}
	
	

	public void change_direction_to_north() {
		try {
			lockSemaphore.acquire();
			direction = Direction.NORTH;
			changeDirection.release();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			lockSemaphore.release();
		}
		
	}

	public void change_direction_to_south() {
		
		try {
			lockSemaphore.acquire();
			direction = Direction.SOUTH;
			changeDirection.release();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			lockSemaphore.release();
		}
	}

	public Direction get_direction() {
		return direction;
	}
}
