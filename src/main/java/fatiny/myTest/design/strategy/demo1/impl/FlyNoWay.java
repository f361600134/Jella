package fatiny.myTest.design.strategy.demo1.impl;

public class FlyNoWay implements FlyBehavior {

	@Override
	public void fly() {
		System.out.println("不会飞");
	}

}
