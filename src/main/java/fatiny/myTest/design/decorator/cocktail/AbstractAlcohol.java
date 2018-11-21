package fatiny.myTest.design.decorator.cocktail;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * @author Jeremy Feng
 * 酒精抽象类
 */
public abstract class AbstractAlcohol implements IAlcohol{
	
	private String name;
	private double price;
	
	private Map<String, Double> map = Maps.newHashMap();
	
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
	
	public double getPrecent(String name){
		return map.get(name);
	}
	
	public AbstractAlcohol(String name, double price) {
		this.name = name;
		this.price = price;
		map.put(name, price);
	}
	
	@Override
	public String getDetail() {
		return getName() + "\n" + getPrice();
	}
}
