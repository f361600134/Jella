package fatiny.myTest.design.decorator.cocktail.domain;

import fatiny.myTest.design.decorator.cocktail.AbstractAlcohol;

/**
 * @author Jeremy Feng
 *
 */
public class Wine extends AbstractAlcohol{

	/**
	 * @param name
	 * @param price
	 * @param alcohol
	 */
	public Wine(String name, double price) {
		super(name, price);
	}

}
