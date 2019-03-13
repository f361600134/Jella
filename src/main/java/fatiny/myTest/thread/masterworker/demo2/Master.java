package fatiny.myTest.thread.masterworker.demo2;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * T 子任务类型, R 返回值类型
 * 
 * @auth Jeremy
 * @date 2019年2月14日下午6:10:08
 */
public class Master<T, R> {
	// 任务队列
	protected Queue<T> workQueue = new ConcurrentLinkedQueue<T>();
	// Worker线程队列
	protected Map<String, Thread> threadMap = new HashMap<String, Thread>();
	// 子任务处理结果集
	protected Queue<R> resultQueue = new ConcurrentLinkedQueue<R>();

	public Master(Worker<T, R> worker, int countWorker) {
		worker.setWorkQueue(workQueue);
		worker.setResultQueue(resultQueue);
		for (int i = 0; i < countWorker; i++) {
			threadMap.put(Integer.toString(i), new Thread(worker, Integer.toString(i)));
		}
	}

	// 是否所有的子任务都加工完成了
	public boolean isComplete() {
		for (Map.Entry<String, Thread> entry : threadMap.entrySet()) {
			if (entry.getValue().getState() != Thread.State.TERMINATED)
				// 存在未完成的线程
				return false;
		}
		return true;
	}

	// 提交一个子任务
	public void submit(T job) {
		workQueue.add(job);
	}

	// 返回子任务结果集
	public Queue<R> getResultQueue() {
		return resultQueue;
	}

	// 执行所有Worker进程，进行处理
	public void execute() {
		for (Map.Entry<String, Thread> entry : threadMap.entrySet()) {
			entry.getValue().start();
		}
	}

	// 销毁对象
	public void destory() {
		workQueue = null;
		threadMap = null;
		resultQueue = null;
	}
}