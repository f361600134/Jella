package fatiny.myTest.design.template.car;

public class BMWCar extends AbstractCar{

	@Override
	public void start() {
		System.out.println("宝马开启了");
	}

	@Override
	public void stop() {
		System.out.println("宝马停车了");
	}

}
