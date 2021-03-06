package fatiny.myTest.thread.future;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.google.common.collect.Lists;

public class TestF {
	public static void main(String[] args) {
		// 第一种方式
		ExecutorService executor = Executors.newCachedThreadPool();
		Task task = new Task();
		Task2 task2 = new Task2();
		Collection<Callable<Integer>> list1 = Lists.newArrayList(task, task2);
		try {
			List<Future<Integer>> futureList = executor.invokeAll(list1);
			for (Future<Integer> list : futureList) {
				System.out.println(list.get());
			}
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		// executor.submit(futureTask);
		executor.shutdown();

		// 第二种方式，注意这种方式和第一种方式效果是类似的，只不过一个使用的是ExecutorService，一个使用的是Thread
		/*
		 * Task task = new Task(); FutureTask<Integer> futureTask = new
		 * FutureTask<Integer>(task); Thread thread = new Thread(futureTask);
		 * thread.start();
		 */
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		System.out.println("主线程在执行任务");

		// try {
		// System.out.println("task运行结果" + futureTask.get());
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// } catch (ExecutionException e) {
		// e.printStackTrace();
		// }

		System.out.println("所有任务执行完毕");
	}
}
