
public class PCMonitor {
	private final int N = 5;
	int oldest = 0, newest = 0;
	volatile int count = 0;
	int buffer[] = new int[N];
	
	synchronized void append(int v) {
		while(count == N) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
		}
		buffer[newest] = v;
		newest = (newest + 1)%N;
		count++;
		notify();
	}
	
	synchronized int take() {
		int temp;
		while(count == 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}
		temp = buffer[oldest];
		oldest = (oldest + 1)%N;
		count--;
		notify();
		return temp;
	}
}
