
public class MainCountSem {

	public static void main(String[] args) {
		CountSem a = new CountSem();
		CountSem b = new CountSem();
		a.start();
		b.start();
		
		try {
			a.join();
			b.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Contor value for thread b is : " + b.getN());
		System.out.println("Contor value for thread a is : " + a.getN());
		

	}

}
