package fatiny.myTest.testTool;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 消费者
 * @author Jeremy
 */
public class Customer implements Runnable{
	
	Queue<Integer> queue = new LinkedBlockingQueue<Integer>();

	@Override
	public void run() {
		
	}
	
	
}
