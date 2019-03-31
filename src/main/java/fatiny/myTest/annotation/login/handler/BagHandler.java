package fatiny.myTest.annotation.login.handler;

import fatiny.myTest.annotation.login.Command;

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
