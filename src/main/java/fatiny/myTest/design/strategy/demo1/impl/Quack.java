package fatiny.myTest.design.strategy.demo1.impl;

public class Quack implements QuackBehavior {

	@Override
	public void quack() {
		System.out.println("嘎嘎叫");

	}

}
