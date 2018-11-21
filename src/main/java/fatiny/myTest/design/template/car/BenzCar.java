package fatiny.myTest.design.template.car;

public class BenzCar extends AbstractCar{

	@Override
	public void start() {
		System.out.println("奔驰开启了");
	}

	/**
	 * 需要重写的,自己去重写
	 */
	@Override
	public void alarm() {
		System.out.println("滴~~~~滴~~~~");
	}

	@Override
	public void stop() {
		System.out.println("奔驰停车了");
	}

}
