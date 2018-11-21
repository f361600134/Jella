package fatiny.myTest.design.facade.computer;

import org.junit.Test;

/**
 * 充当测试单元
 * @author Administrator
 */
public class Client {
	
	@Test
	public void test(){
		
		Computer computer = new Computer();
		computer.start();
		System.out.println();
		computer.shutdown();
		
	}

}
