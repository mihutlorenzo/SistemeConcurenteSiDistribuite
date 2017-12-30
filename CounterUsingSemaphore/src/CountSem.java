import java.util.concurrent.Semaphore;

public class CountSem extends Thread{
	static volatile int n = 0;
	static Semaphore s = new Semaphore(1);
	
	int getN()
	{
		return n;
	}
	
	public void run()
	{
		int temp;
		for(int i = 0;i<10000000;i++)
		{
			try {
				s.acquire();
				temp = n;
				n = temp+1;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				s.release();
			}
			
			
		}
	}
	
}
