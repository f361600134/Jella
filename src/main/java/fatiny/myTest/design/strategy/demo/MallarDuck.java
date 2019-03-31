package fatiny.myTest.design.strategy.demo;

public class MallarDuck extends Duck {

	@Override
	public void fly() {
		System.out.println("横着飞");
	}

	@Override
	public void swim() {
		System.out.println("潜泳");
	}

	@Override
	public void quack() {
		System.out.println("嘎嘎");
	}

	@Override
	public void display() {
		System.out.println("灰色");
	}

}
