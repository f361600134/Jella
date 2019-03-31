package fatiny.myTest.design.strategy.demo;

public class RedHeadDuck extends Duck {

	@Override
	public void fly() {
		System.out.println("竖着飞");
	}

	@Override
	public void swim() {
		System.out.println("浮着游");
	}

	@Override
	public void quack() {
		System.out.println("呷呷");
	}

	@Override
	public void display() {
		System.out.println("红色头");
	}

}
