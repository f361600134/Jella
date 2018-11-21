package fatiny.myTest.design.adapter.aboutclass.demo1;

import org.junit.Test;

public class TestAdapter {
	
	@Test
	public void test(){
		Target target = new Adapter();
		target.operation1();
		target.operation2();
	}

}
