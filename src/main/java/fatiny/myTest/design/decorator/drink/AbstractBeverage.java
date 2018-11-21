package fatiny.myTest.design.decorator.drink;

/**
 * @author Jeremy Feng
 *
 */
public abstract class AbstractBeverage implements IBeverage{
	
	private String info;
	private double price;
	
	public AbstractBeverage(String info, double price) {
		super();
		this.info = info;
		this.price = price;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "AbstractBeverage [info=" + info + ", price=" + price + "]";
	}

	@Override
	public String getDetail() {
		return getInfo() +"\n"+ getPrice();
	}

	
}
