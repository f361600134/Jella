package fatiny.myTest.design.strategy.demo;

public class RubberDuck extends Duck {

	// @Override
	// public void fly() {
	// System.out.println("不会飞");
	// }

	@Override
	public void swim() {
		System.out.println("不会游");
	}

	@Override
	public void quack() {
		System.out.println("不会叫");
	}

	@Override
	public void display() {
		System.out.println("小黄鸭");
	}

}
