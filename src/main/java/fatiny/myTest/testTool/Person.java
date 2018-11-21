package fatiny.myTest.testTool;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class Person implements Runnable{
	
	Queue<Integer> queue = new LinkedBlockingQueue<Integer>();
	
	public Person(Queue<Integer> queue){
		this.queue = queue;
	}

	/**
	 * 写入数据
	 */
	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			this.queue.offer(i);
		}
	}

}
