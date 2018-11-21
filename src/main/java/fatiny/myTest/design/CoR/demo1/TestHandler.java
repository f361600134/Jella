package fatiny.myTest.design.CoR.demo1;

import org.junit.Test;

public class TestHandler {
	
	@Test
	public void test(){
		Handler Jeremy = new ConcreteHandler("Jeremy");
		Handler Tom = new ConcreteHandler("Tom");
		Jeremy.setNextHandler(Tom);
		Jeremy.doHandler();
	}

}
