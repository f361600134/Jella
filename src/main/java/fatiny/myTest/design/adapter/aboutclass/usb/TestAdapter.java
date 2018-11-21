package fatiny.myTest.design.adapter.aboutclass.usb;

import org.junit.Test;

public class TestAdapter {
	
	@Test
	public void test(){
		PS2 ps2 = new PS2Adapter();
		ps2.isPS2();
	}

}
