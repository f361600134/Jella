package fatiny.myTest.design.CoR.workflow;

import org.junit.Test;

public class TestCoR {
	
	@Test
	public void test(){
		HandlerWork vp = new HandlerVP();
		HandlerWork cto = new HandlerCTO();
		HandlerWork manager = new HandlerManager();
		
		manager.setNextHandler(cto);
		cto.setNextHandler(vp);
		
		int day = 5;
		manager.doHandler(day);
		
	}
	
}
