package fatiny.myTest.design.decorator.cocktail.domain;

import fatiny.myTest.design.decorator.cocktail.IAlcohol;

/**
 * @author Jeremy Feng
 * <a href="http://blog.csdn.net/zhshulin/article/details/38665187">相关链接</a>
 */
public class Test {
	
	public static void main(String[] args) {
		
		IAlcohol alcohol = new Wine("Whisky", 100);
		
		alcohol = new Drink("Milk",30, alcohol);
		
		alcohol = new Drink("Rio",10, alcohol);
		
		System.out.println(alcohol.getDetail());
		
		System.out.println(alcohol.getPrecent("Whisky"));
		
	}

}
