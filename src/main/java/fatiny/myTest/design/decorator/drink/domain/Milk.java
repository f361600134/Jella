package fatiny.myTest.design.decorator.drink.domain;

import fatiny.myTest.design.decorator.drink.AbstractBeverage;
import fatiny.myTest.design.decorator.drink.IBeverage;

/**
 * @author Jeremy Feng
 *
 */
public class Milk extends AbstractBeverage{
	
	private final static String info = "加了牛奶";
	private final static int price = 20;
	
	private IBeverage beverage = null;
	
	public Milk(IBeverage beverage) {
		super(info, price);
		this.beverage = beverage;
	}

	@Override
	public double getPrice() {
		return beverage.getPrice() + price;
	}

	@Override
	public String getInfo() {
		return beverage.getInfo() + "\n" + info;
	}
	
}
