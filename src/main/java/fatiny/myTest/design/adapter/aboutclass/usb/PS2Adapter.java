package fatiny.myTest.design.adapter.aboutclass.usb;

public class PS2Adapter extends USBer implements PS2{

	@Override
	public void isPS2() {
		isUSB();
	}

}
