package fatiny.myTest.design.strategy.demo1;

import fatiny.myTest.design.strategy.demo1.impl.FlyWithWing;
import fatiny.myTest.design.strategy.demo1.impl.Quack;

public class Main {

	public static void main(String[] args) {
		Duck duck = new MallarDuck(new FlyWithWing(), new Quack());
		duck.fly();
		duck.swim();
		duck.quack();
		duck.display();
	}

}
