package fatiny.myTest.design.strategy.demo;

public class Main {

	public static void main(String[] args) {
		Duck duck = new MallarDuck();
		duck.fly();
		duck.swim();
		duck.quack();
		duck.display();

		duck = new RedHeadDuck();
		duck.fly();
		duck.swim();
		duck.quack();
		duck.display();

		duck = new RubberDuck();
		duck.fly();
		duck.swim();
		duck.quack();
		duck.display();

	}

}
