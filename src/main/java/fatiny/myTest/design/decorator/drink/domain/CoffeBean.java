package fatiny.myTest.design.decorator.drink.domain;

import fatiny.myTest.design.decorator.drink.AbstractBeverage;

/**
 * @author Jeremy Feng
 * 咖啡豆
 */
public class CoffeBean extends AbstractBeverage{
	
	/**
	 * @param info
	 */
	public CoffeBean(String info, double price) {
		super(info, price);
	}

	@Override
	public double getPrice() {
		return super.getPrice();
	}

	@Override
	public String getInfo() {
		return super.getInfo();
	}

}
