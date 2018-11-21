package fatiny.myTest.design.decorator.drink;

/**
 * @author Jeremy Feng
 * 饮料接口
 */
public interface IBeverage {
	
	//价格
	public double getPrice();

	//信息
	public String getInfo();
	
	//清单
	public String getDetail();
}
