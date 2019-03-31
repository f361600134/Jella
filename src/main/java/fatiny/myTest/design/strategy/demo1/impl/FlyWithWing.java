package fatiny.myTest.design.strategy.demo1.impl;

public class FlyWithWing implements FlyBehavior {

	@Override
	public void fly() {
		System.out.println("横着飞");
	}

}
