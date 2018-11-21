package fatiny.myTest.design.adapter.aboutobject.usb;

/**
 * 对象适配器
 * @author Administrator
 */
public class PS2Adapter implements PS2{

	private USB usb;
	public PS2Adapter(USB usb) {
		super();
		this.usb = usb;
	}

	@Override
	public void isPS2() {
		usb.isUSB();
	}

}
