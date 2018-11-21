package fatiny.myTest.design.adapter.aboutclass.demo1;

/**
 * 适配器.
 * 假设,客户端需要从源接口Adapter中调用operation1 和 operation2两个接口.
 * 但是源接口只存在operation1接口, 这个时候需要一个类适配器
 * @author Administrator
 */
public class Adapter extends Adaptee implements Target{

	@Override
	public void operation2() {
		System.out.println("这里是Adapter.operation2");
	}


}
