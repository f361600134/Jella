package fatiny.myTest.design.flyweight.chess;

import org.junit.Test;

public class TestFlyWeight {
	
	@Test
	public void test(){
		
		ChessFlyWeightFactory factory = new ChessFlyWeightFactory();
		ChessFlyWeight chess1 = factory.getChess("black");
		ChessFlyWeight chess2 = factory.getChess("black");
		ChessFlyWeight chess3 = factory.getChess("white");
		ChessFlyWeight chess4 = factory.getChess("white");
		
		System.out.println(chess1 == chess2);
		System.out.println(chess2 == chess3);
		System.out.println(chess3 == chess4);
		
		chess1.display(new Coordinate(10,20));
		chess2.display(new Coordinate(20,30));
		chess3.display(new Coordinate(15,30));
		chess4.display(new Coordinate(20,20));
		
		//System.out.println(factory.size());
		System.out.println(chess1.getColor());
		System.out.println(chess2.getColor());
		System.out.println(chess3.getColor());
		System.out.println(chess4.getColor());
		System.out.println(ChessFlyWeightFactory.size());
	}
	
}
