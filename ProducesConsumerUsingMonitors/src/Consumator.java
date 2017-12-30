
public class Consumator extends Thread {
	int q,r,max,min;
	PCMonitor buffer;
	int c = 0;
	public Consumator(int q,int r,int min,int max,PCMonitor b) {
		this.q = q;
		this.r = r;
		this.min =min;
		this.max = max;
		this.buffer = b;
	}
	
	int getCounter() {
		return c;
	}
	
	public void run() {
		int i = min,k;
		while((i % q) != r) {
			i++;
		}
		while(i <= max) {
			k = buffer.take();
			c++;
			System.out.println("Consumer " +r+ " consumed " + k);
			i +=q;
		}
	}
	
}
