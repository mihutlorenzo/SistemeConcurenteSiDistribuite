public class Chronometer implements Runnable
{
	private long last;
	private Bridge bridge;
	private long begin;
	private long end;
	
	public Chronometer(Bridge monitor)
	{
		last = -1;
		this.bridge = monitor;
		begin = System.currentTimeMillis();
	}

	public void run()
	{
		while(!OneLaneBridge.get_finish())
		{
			get_time();
		}
	}

	public void get_time()
	{
		long passTime = 0;

		double time = define_time();
		passTime = (long) (time / 1000.0);
		
		if(passTime % 2 == 0 && passTime != last) 
		{
			last = passTime; 
			if(bridge.get_direction() == Direction.SOUTH) bridge.change_direction_to_north();
			else bridge.change_direction_to_south();
			System.out.println(passTime +" seconds ("+bridge.get_direction()+")");
			System.out.println();
		}

	}
	
	private long define_time() 
	{
		return ((end = System.currentTimeMillis()) - begin);
	}
}