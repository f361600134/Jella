package fatiny.myTest.thread.future;

import java.util.concurrent.Callable;

public class Task2 implements Callable<Integer> {
	@Override
	public Integer call() throws Exception {
		System.out.println("子线程在进行计算");
		Thread.sleep(2000);
		int sum = 0;
		for (int i = 0; i < 100; i++)
			sum += i;
		System.out.println("Task2子线程计算结果:" + sum);
		return sum;
	}
}
