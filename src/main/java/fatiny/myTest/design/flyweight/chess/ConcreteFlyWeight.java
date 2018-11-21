package fatiny.myTest.design.flyweight.chess;

/**
 * 实体类
 * @author Administrator
 *
 */
public class ConcreteFlyWeight implements ChessFlyWeight{
	
	private String color;
	public ConcreteFlyWeight(String color) {
		super();
		this.color = color;
	}

	@Override
	public void color(String color) {
		// TODO Auto-generated method stub
	}

	@Override
	public String getColor() {
		return color;
	}

	@Override
	public void display(Coordinate c) {
		System.out.println("棋子的颜色:"+color + ", 棋子的坐标:"+c);
	}
	
	
	
}
