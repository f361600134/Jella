package fatiny.myTest.design.adapter.aboutobject.usb;

import org.junit.Test;

public class TestAdapter {
	
	@Test
	public void test(){
		PS2 ps2 = new PS2Adapter(new USBer());
		ps2.isPS2();
	}

}
