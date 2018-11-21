package fatiny.myTest.annotation.handler;

import fatiny.myTest.annotation.Command;

/**
 * @author Jeremy Feng
 *
 */
public class BagHandler {
	
	@Command(id = 1, mustLogin = false)
	public void listBag(){
		System.out.println("show bag");
	}

	@Command(id = 2)
	public void updateBag(){
		System.out.println("update bag");
	}
	
	
}
