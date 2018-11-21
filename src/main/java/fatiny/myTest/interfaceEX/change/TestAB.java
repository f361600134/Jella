package fatiny.myTest.interfaceEX.change;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jeremy Feng
 *
 */
public class TestAB {
	
	public static void main(String[] args) {
//		IA ab = new AB();
//		IB bc = (IB)ab;
//		bc.printB();
		
		List<IA> list = new ArrayList<IA>();
		list.add(new AB());
		for (IA ia : list) {
			IB bc = (IB)ia;
			bc.printB();
		}
	}
}
