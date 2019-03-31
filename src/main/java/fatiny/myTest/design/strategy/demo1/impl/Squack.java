package fatiny.myTest.design.strategy.demo1.impl;

public class Squack implements QuackBehavior {

	@Override
	public void quack() {
		System.out.println("呷呷叫");
	}

}
