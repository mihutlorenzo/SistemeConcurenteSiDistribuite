import java.util.concurrent.CountDownLatch;

public class Main {
	private static Waiter[] waiters;
	private static Decrementer[] decrementers;
	private static CountDownLatch latch;
	private static Thread[] decrementer_threads;
	private static Thread[] waiter_threads;
	private static int counter = 15;
	private static int n_waiters = 20;
	private static int n_decrementers = 3;

	public static void main(String[] args) {
		latch = new CountDownLatch(counter);
		decrementers = new Decrementer[n_decrementers];
		decrementer_threads = new Thread[n_decrementers];
		waiters = new Waiter[n_waiters];
		waiter_threads = new Thread[n_waiters];
		for(int i = 0;i<n_decrementers;i++) {
			decrementers[i] = new Decrementer(latch,counter/n_decrementers, i+1);
			decrementer_threads[i] = new Thread(decrementers[i]);
		}
		
		for(int i = 0;i<n_waiters;i++) {
			waiters[i] = new Waiter(latch, i+1);
			waiter_threads[i] = new Thread(waiters[i]);
		}
		
		for(int i = 0;i<n_decrementers;i++) {
			decrementer_threads[i].start();
		}
		
		for(int i = 0;i<n_waiters;i++) {
			waiter_threads[i].start();
		}
		
		for(int i = 0;i<n_decrementers;i++) {
			try {
				decrementer_threads[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		for(int i = 0;i<n_waiters;i++) {
			try {
				waiter_threads[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
