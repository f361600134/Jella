package fatiny.myTest.design.facade.computer;

public class CPU implements Operation{

	@Override
	public void start() {
		System.out.println("CPU已启动");
	}

	@Override
	public void shutdown() {
		System.out.println("CPU已关闭");
	}

}
