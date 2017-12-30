import java.util.concurrent.TimeUnit;

public class SingleLaneBridge {

	public static void main(String[] args) {
		 final Bridge bridge = new Bridge(5);  
		 Thread leftSide = new Thread(new SideVehicle(Direction.LEFT, 30, bridge));
		 Thread rightSide = new Thread(new SideVehicle(Direction.RIGHT, 50, bridge));
		leftSide.start();
		rightSide.start();
	       /* Thread South = new Thread(new Runnable()  //constructs South bound running thread
	        {
	            @Override
	            public void run()//method that is implemented in a Runnable interface
	            {
	                while(true)
	                {
	                	int i;
	                    Vehicle vehicle = new Vehicle(bridge,1);  //constructs new vehicle from Vehicle class, sets to vehicle and uses bridge parameter
	                    Thread a = new Thread(vehicle);  //vehicle object becomes thread object
	                    vehicle.setName("South Vehicle: "+a.getId());  //sets vehicle name to South Vehicle and gets the thread ID
	                    a.start();  //starts the thread
	                    try  //try catch block
	                    {    //performs a Thread sleep for a given time
	                        TimeUnit.SECONDS.sleep((long)(Math.random()*10));
	                    }
	                    catch(InterruptedException iex)
	                    {  //prints the stack trace of the Exception to System.err.  Helps diagnose Exception.
	                        iex.printStackTrace();
	                    }
	                }
	            }
	        });
	        Thread North = new Thread(new Runnable() //constructs North bound running thread
	        {
	            @Override
	            public void run()//method that is implemented in a Runnable interface
	            {
	                while(true)
	                {
	                    Vehicle vehicle = new Vehicle(bridge,2);  //constructs new vehicle from Vehicle class, sets to vehicle and uses bridge parameter
	                    Thread a = new Thread(vehicle);  //vehicle object becomes thread object
	                    vehicle.setName("North Vehicle:  "+a.getId());  //sets vehicle name to North Vehicle and gets the thread ID
	                    a.start();  //starts thread
	                    try
	                    {   //performs a Thread sleep for a given time
	                        TimeUnit.SECONDS.sleep((long)(Math.random()*10));
	                    }
	                    catch(InterruptedException iex)
	                    {  //prints the stack trace of the Exception to System.err.  Helps diagnose Exception.
	                        iex.printStackTrace();        
	                    }
	                }
	            }
	        });
	        South.start();  //starts south thread
	        North.start(); //starts north thread*/

	}

}
