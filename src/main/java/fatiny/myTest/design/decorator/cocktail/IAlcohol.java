package fatiny.myTest.design.decorator.cocktail;

/**
 * @author Jeremy Feng
 * 酒精接口
 */
public interface IAlcohol {
	
	//名字
	public String getName();
		
	//价格
	public double getPrice();

	//清单
	public String getDetail();
	
	//获取比例
	public double getPrecent(String name);
	
}
