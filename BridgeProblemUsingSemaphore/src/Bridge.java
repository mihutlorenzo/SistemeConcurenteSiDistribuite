import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

public class Bridge {
	private Direction direction;
	private final Lock lock;
	private final Condition north;
	private final Condition south;

	public Bridge(Direction direction) {
		this.direction = direction;

		lock = new ReentrantLock(true);
		north = lock.newCondition();
		south = lock.newCondition();

	}

	public void crossBridge(Direction carDirection) throws InterruptedException {
		
		if (carDirection != direction) {
			lock.lock();

			try {

				if (carDirection == Direction.SOUTH)
					while (direction == Direction.NORTH)
						south.await();
				else
					while (direction == Direction.NORTH)
						north.await();

			} finally {
				lock.unlock();
			}
		}

	}

	public void change_direction_to_north() {
		lock.lock();
		try {
			direction = Direction.NORTH;
			north.signal();
		} finally {
			lock.unlock();
		}
	}

	public void change_direction_to_south() {
		lock.lock();
		try {
			direction = Direction.SOUTH;
			south.signal();
		} finally {
			lock.unlock();
		}
	}

	public Direction get_direction() {
		return direction;
	}
}
