package fatiny.myTest.design.CoR.workflow1;

import org.junit.Test;

public class HandlerTest {
	
	@Test
	public void test(){
		
		HandlerChain chain = new HandlerChain();
		chain.appendWork(new HandlerManager())
			.appendWork(new HandlerCTO())
			.appendWork(new HandlerVP());
		
		int day = 5;
		chain.doHandler(day);
	}

}
