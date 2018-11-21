package fatiny.myTest.design.decorator.drink.domain;

import fatiny.myTest.design.decorator.drink.AbstractBeverage;
import fatiny.myTest.design.decorator.drink.IBeverage;

/**
 * @author Jeremy Feng
 * 奶泡
 */
public class Foam extends AbstractBeverage{
	
	private final static String info = "加了奶泡";
	private final static int price = 30;
	
	private IBeverage beverage = null;
	
	public Foam(IBeverage beverage) {
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
