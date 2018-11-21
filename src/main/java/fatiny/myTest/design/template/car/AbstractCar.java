package fatiny.myTest.design.template.car;

public abstract class AbstractCar {
	
	public final void run(){
		start();
		alarm();
		stop();
	}

	public abstract void start();
	
	public void alarm(){
		System.out.println("滴滴滴滴滴滴滴滴");
	}
	
	public abstract void stop();
	
}
