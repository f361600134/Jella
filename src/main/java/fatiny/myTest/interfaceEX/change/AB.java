
package fatiny.myTest.interfaceEX.change;

/**
 * @author Jeremy Feng
 *
 */
public class AB implements IA, IB{

	@Override
	public void printA() {
		System.out.println("AAAAAAAA");		
	}

	@Override
	public void printB() {
		System.out.println("BBBBBBBB");		
	}
	
}
