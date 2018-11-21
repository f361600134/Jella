package fatiny.myTest.design.decorator.cocktail.domain;

import fatiny.myTest.design.decorator.cocktail.AbstractAlcohol;
import fatiny.myTest.design.decorator.cocktail.IAlcohol;

/**
 * @author Jeremy Feng
 *
 */
public class Drink extends AbstractAlcohol{

	private IAlcohol alcohol;
	
	/**
	 * @param name
	 * @param price
	 */
	public Drink(String name, double price, IAlcohol alcohol) {
		super(name, price);
		this.alcohol = alcohol;
	}

	@Override
	public String getName() {
		return alcohol.getName() +"\n"+ super.getName();
	}

	@Override
	public double getPrice() {
		return super.getPrice()+ alcohol.getPrice();
	}
	
	

}
