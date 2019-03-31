package fatiny.myTest.annotation.login.handler;

import fatiny.myTest.annotation.login.Command;

/**
 * @author Jeremy Feng
 *
 */
public class WeaponHandler {
	
	@Command(id = 3)
	public void listWea(){
		System.out.println("show weapon");
	}

	@Command(id = 4)
	public void updateWea(){
		System.out.println("update weapon");
	}

}
