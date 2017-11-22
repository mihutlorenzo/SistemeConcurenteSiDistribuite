import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Filter implements Lock {
	private AtomicInteger[] level;
	private AtomicInteger[] victim;

	private int numberOfThreads;
	private int threadId;

	public Filter(int length) {
		this.numberOfThreads = length;
		level = new AtomicInteger[length];
		victim = new AtomicInteger[length];
		for (int i = 0; i < numberOfThreads; i++) {
			level[i] = new AtomicInteger();
			victim[i] = new AtomicInteger();
		}
	}

	@Override
	public void lock() {
		int me = threadId;
		System.out.println("Thread id" + me);
		for (int i = 1; i < numberOfThreads; i++) {
			level[me].set(i);
			victim[i].set(me);
			for (int k = 0; k < numberOfThreads; k++) {
				while ((k != me) && (level[k].get() >= i && victim[i].get() == me)) {

				}
			}
		}

	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
		// TODO Auto-generated method stub

	}

	@Override
	public Condition newCondition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean tryLock() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean tryLock(long arg0, TimeUnit arg1) throws InterruptedException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void unlock() {
		int me = threadId;
		level[me].set(0);

	}

	public void setIndex(int threadIndex) {
		this.threadId = threadIndex;

	}

}
