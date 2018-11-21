package fatiny.myTest.design.decorator.cocktail;

/**
 * @author Jeremy Feng
 *
 */
public abstract class Mixture {
	
	private String name;
	private double price;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public Mixture(String name, double price) {
		super();
		this.name = name;
		this.price = price;
	}
	
}
