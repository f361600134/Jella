package fatiny.myTest.design.facade.computer;

public class Disk implements Operation{
	
	@Override
	public void start() {
		System.out.println("Dick已启动");
	}

	@Override
	public void shutdown() {
		System.out.println("Dick已关闭");
	}

}
