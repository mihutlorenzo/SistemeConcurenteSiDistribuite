
public class Car implements Runnable{
	private int carId;
	private String name;
    private Bridge bridge;
    public Car(Bridge bridge,int carId)
    {
        this.bridge = bridge;  //assigns value of parameter bridge to variable with same name
        this.carId = carId;
    }
    //method that is implemented when there is a Runnable interface
    public void run()
    {
        bridge.startCrossBridge(this);
        bridge.endCrossBridge();
    }
    //get method for vehicle name
    public String getName()
    {
        return name;
    }
	public void setName(String string) {
		this.name = string;
		
	}
   
}
