package fatiny.myTest.design.decorator.drink.domain;

import fatiny.myTest.design.decorator.drink.IBeverage;

/**
 * @author Jeremy Feng
 *
 */
public class Test {
	
	public static void main(String[] args) {
		//Cubita
		IBeverage beverage = new CoffeBean("咖啡豆", 100);
		beverage = new Milk(beverage);
		beverage = new Foam(beverage);
		System.out.println(beverage.getDetail());
	}

}
