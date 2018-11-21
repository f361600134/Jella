package fatiny.myTest.design.decorator.drink.domain;

import fatiny.myTest.design.decorator.drink.AbstractBeverage;

/**
 * @author Jeremy Feng
 * 装饰类
 */
public class Decorator extends AbstractBeverage {
	
	//private String info = "I'm a decorator"; 
	
	/**
	 * @param info
	 */
	public Decorator(String info, double price) {
		super(info, price);
	}

	@Override
	public double getPrice() {
		return getPrice();
	}

	@Override
	public String getInfo() {
		return getInfo();
	}
	
}
