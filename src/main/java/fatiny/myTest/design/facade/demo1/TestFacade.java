package fatiny.myTest.design.facade.demo1;

import org.junit.Test;

public class TestFacade {
	
	@Test
	public void test(){
		
		Facade facade = new Facade();
		facade.callA();
		facade.callB();
		facade.callC();
		
	}

}
