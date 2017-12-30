import java.util.concurrent.CountDownLatch;

public class Waiter implements Runnable {
	CountDownLatch latch = null;
	int idx;
	
	public Waiter(CountDownLatch latch ,int i)
	{
		this.latch = latch;
		idx = i;
	}

	@Override
	public void run() {
		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Waiter " +idx+ " released");
	}

}
