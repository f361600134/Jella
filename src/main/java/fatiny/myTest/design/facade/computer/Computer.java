package fatiny.myTest.design.facade.computer;

/**
 * 充当门面
 * @author Administrator
 */
public class Computer implements Operation{
	
	private CPU cpu;
	private Memory memory;
	private Disk disk;
	
	public Computer(){
		cpu = new CPU();
		memory = new Memory();
		disk = new Disk();
	}
	
	@Override
	public void start() {
		cpu.start();
		memory.start();
		disk.start();
		System.out.println("Computer已启动");
	}

	@Override
	public void shutdown() {
		cpu.shutdown();
		memory.shutdown();
		disk.shutdown();
		System.out.println("Computer已关闭");
	}

}
