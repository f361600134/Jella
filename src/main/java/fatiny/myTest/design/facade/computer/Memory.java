package fatiny.myTest.design.facade.computer;

public class Memory implements Operation{
	
	@Override
	public void start() {
		System.out.println("Memory已启动");
	}

	@Override
	public void shutdown() {
		System.out.println("Memory已关闭");
	}

}
