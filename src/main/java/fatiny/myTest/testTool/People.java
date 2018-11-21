package fatiny.myTest.testTool;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class People implements Runnable{
	
	private int startNum;
	private int endNum;
	private String name;
	Queue<Integer> queue = new LinkedBlockingQueue<Integer>();
	
	public People(Queue<Integer> queue, String name, int startNum, int endNum){
		this.queue = queue;
		this.name = name;
		this.startNum = startNum;
		this.endNum = endNum;
	}
	
	public String getName(){
		return name;
	}

	/**
	 * 写入数据
	 */
	@Override
	public void run() {
//		long startTime = System.currentTimeMillis();
		for (int i = startNum; i < endNum; i++) {
			this.queue.offer(i);
		}
//		long offerTime = System.currentTimeMillis();
		
		Integer playerId = queue.poll();
		while (playerId != null) {
			playerId = queue.poll();
		}
//		long pollTime = System.currentTimeMillis() - offerTime;
//		if (name.equals("A1")) {
//			System.out.println("线程:"+getName() +", offer用时:"+(offerTime-startTime) +", poll用时:"+pollTime);
//		}
	}
	
	public static void main(String[] args) {
		testA();
		testB();
	}
	
	static void testA(){
//		String jeremy = "Jeremy";
//		String and = " and ";
//		String lora = "Lora";
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < 50000; i++) {
//			String str =  jeremy + and + lora;
			String str =  "jeremy" + " and " + "lora";
		}
		System.out.println(""+(System.currentTimeMillis() - startTime));
	}
	
	static void testB(){
		long startTime = System.currentTimeMillis();
		String jeremy = "Jeremy";
		String and = " and ";
		String lora = "Lora";
		
		StringBuilder str = null;
		for (int i = 0; i < 50000; i++) {
			str = new StringBuilder(jeremy).append(and).append(lora);
		}
		System.out.println(""+(System.currentTimeMillis() - startTime));
	}

}
