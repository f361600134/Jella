package fatiny.myTest.design.flyweight.chess;

/**
 * 接口类
 * @author Administrator
 *
 */
public interface ChessFlyWeight {
	
	void color(String color);
	
	String getColor();
	
	void display(Coordinate c);
}
