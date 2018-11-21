package fatiny.myTest.other.showdownevent;

import java.util.concurrent.TimeUnit;

/**
 * @author Jeremy Feng
 *
 */
public class ShowdownTest {
	
	public static void main(String[] args) {
		try {

			ShowdownTest test = new ShowdownTest();
			test.prepareShutdownThread();
			System.out.println("系统启动成功...");
			
			TimeUnit.SECONDS.sleep(2);
			
//			System.exit(0);
//			System.out.println("退出成功");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void prepareShutdownThread(){
		Runtime.getRuntime().addShutdownHook(new showdownTask());
	}
	
	/**
	 * 服务器关闭事件
	 * @author Jeremy Feng
	 */
	class showdownTask extends Thread{
		@Override
		public void run() {
			try {
				System.out.println("正在保存玩家数据....");
				TimeUnit.SECONDS.sleep(2);
				System.out.println("玩家数据保存成功,进程关闭");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	

}
