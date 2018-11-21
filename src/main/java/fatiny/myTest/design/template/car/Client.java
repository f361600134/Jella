
package fatiny.myTest.design.template.car;

import org.junit.Test;

public class Client {
	
	@Test
	public void testCar(){
		
		AbstractCar bmw = new BMWCar();
		bmw.run();
		
		AbstractCar benz = new BenzCar();
		benz.run();
		
	}
	
}
