import java.util.concurrent.CountDownLatch;

public class Decrementer implements Runnable {
	int counter ,idx;
	CountDownLatch latch = null;
	
	public Decrementer(CountDownLatch latch, int counter,int i)
	{
		this.latch = latch;
		this.counter = counter;
		idx = i;
	}

	@Override
	public void run() {
		try {
			for(int i = counter;i>0;i--)
			{
				Thread.sleep(1000);
				latch.countDown();
				System.out.println("Decrementer " + idx + " step : " + i);
			}
		}catch(InterruptedException e) {
			e.printStackTrace();
		}

	}

}
