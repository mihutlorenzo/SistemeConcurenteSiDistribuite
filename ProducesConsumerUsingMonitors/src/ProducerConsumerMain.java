
public class ProducerConsumerMain {
	private static final int NPTHREADS = 10;
	private static final int NCTHREADS = 5;
	private static final int MIN = 0;
	private static final int MAX = 76;
	private static Producator pThreads[] = new Producator[NPTHREADS];
	private static Consumator cThreads[]  = new Consumator[NCTHREADS];
	static PCMonitor buffer =  new PCMonitor();
 
	public static void main(String[] args) {
		int i;
		for(i = 0; i<NPTHREADS;i++) {
			pThreads[i] = new Producator(NPTHREADS, i, MIN, MAX, buffer);
			pThreads[i].start();
		}
		for(i = 0; i<NCTHREADS;i++) {
			cThreads[i] = new Consumator(NCTHREADS, i, MIN, MAX, buffer);
			cThreads[i].start();
		}
		for(i = 0; i<NPTHREADS;i++) {
			try {
				pThreads[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for(i = 0; i<NCTHREADS;i++) {
			try {
				cThreads[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		for(i = 0; i<NPTHREADS;i++) {
			System.out.println("Producator " + i + " prouduced " + pThreads[i].getCounter() + " elements");
		}
		for(i = 0; i<NCTHREADS;i++) {
			System.out.println("Consumer " + i + " consumed " + cThreads[i].getCounter()+ " elements");
		}

	}

}
