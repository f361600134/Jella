package fatiny.myTest.design.strategy.demo1;

import fatiny.myTest.design.strategy.demo1.impl.FlyBehavior;
import fatiny.myTest.design.strategy.demo1.impl.QuackBehavior;

public class MallarDuck extends Duck {

	public MallarDuck(FlyBehavior flyBehavior, QuackBehavior quackBehavior) {
		super(flyBehavior, quackBehavior);
	}

	@Override
	public void swim() {
		System.out.println("潜泳");
	}

	@Override
	public void display() {
		System.out.println("灰色");
	}

}
