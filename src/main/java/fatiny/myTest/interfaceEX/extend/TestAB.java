package fatiny.myTest.interfaceEX.extend;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jeremy Feng
 *
 */
public class TestAB {
	
	public static void main(String[] args) {
		List<IBase> list = new ArrayList<IBase>();
		list.add(new EntityImpl());
		for (IBase ia : list) {
			IExtend bc = (IExtend)ia;
			bc.print();
		}
	}
}
