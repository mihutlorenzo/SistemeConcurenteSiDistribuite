
public class Producator extends Thread {
	int q,r,max,min;
	PCMonitor buffer;
	int c = 0;
	public Producator(int q,int r,int min,int max, PCMonitor b) {
		this.q = q;
		this.r =r;
		this.min = min;
		this.max = max;
		this.buffer = b;
	}
	
	public int getCounter()
	{
		return c;
	}
	
	public void run() {
		int i = min;
		while((i % q) != r) {
			i++;
		}
		while(i <= max) {
			buffer.append(i);
			c++;
			System.out.println("Producer " +r+ " produced " +i);
			i +=q;
		}
	}
}
