package fatiny.myTest.testTool;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

import com.google.common.collect.Lists;

public class Aumion2 {
	
	public void testBlocking(){
		LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<Integer>();
		queue.offer(1);
		queue.poll();
		processTest(queue);
		
	}
	
	public void testNotBlocking(){
		AtomicInteger i = new AtomicInteger();
		i.incrementAndGet();
		i.get();
		
		ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<Integer>();
		queue.offer(1);
		queue.poll();
		processTest(queue);
	}
	
	@Test
	public void doTest(){
		testNotBlocking();
	}
	
	
	public void processTest(Queue<Integer> queue){
		for (int i = 0; i < 10; i++) {
			doTest(queue);
		}
		System.out.println("offer 平均时长:"+(off/10) +", poll平均时长:"+(poll/10));
	}
	
	static long off = 0L, poll = 0L;
	public void doTest(Queue<Integer> queue) {
		long startTime = System.currentTimeMillis(); 
		for (int i = 0; i < 100000; i++) {
			queue.offer(i);
		}
		long offerTime =  System.currentTimeMillis();
		
		/*
		 * 理论上来说, 这种循环方式确实不错, 因为只需要取出数据一次.不需要再去内存里面取出第二次数据.
		 */
		Integer playerId = queue.poll();
		while (playerId != null) {
			playerId = queue.poll();
		}
		long pollTime =  System.currentTimeMillis();
		off += (offerTime - startTime);
		poll += (pollTime - offerTime);
		System.out.println("offerTime:"+(offerTime - startTime)+"ms, pollTime:"+(pollTime-offerTime)+"ms");
	}
	
	public static void main(String[] args) {
		LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<Integer>();
//		ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<Integer>();
		//计算多次平均值
		int number = 5;
		for (int i = 0; i < number; i++) {
			doMutilTest(queue);
		}
		System.out.println(number+"次平均耗时"+off/number+"ms");
	}
	
	public static void doMutilTest(Queue<Integer> queue) {
		List<People> peopleList = Lists.newArrayList();
		int startNum = 1000000, endNum = startNum;
		//线程数量
		int threadNum = 10;
		People people = null;
		for (int i = 5; i < threadNum; i++) {
			people = new People(queue, "A"+i, startNum * i , startNum * i +endNum);
			peopleList.add(people);
		}
		
		long startTime = System.currentTimeMillis();
		List<Thread> threads = Lists.newArrayList();
		for (People p : peopleList) {
			Thread thread = new Thread(p);
			threads.add(thread);
			thread.start();
		}
		//线程需要单独的join, 不然只能等到上一条线程执行完之后才能继续执行下去
		for (Thread thread : threads) {
			try {
				thread.join();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		System.out.println("本次耗时Time:"+(System.currentTimeMillis() - startTime));
		off += (System.currentTimeMillis() - startTime);
	}
}




















