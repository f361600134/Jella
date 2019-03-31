package fatiny.myTest.thread.masterworker.demo2;

import java.util.Queue;

public abstract class Worker<T, R> implements Runnable {
	protected Queue<T> workQueue;// 从master那里获得的工作加工队列
	protected Queue<R> resultQueue;

	public void setWorkQueue(Queue<T> workQueue) {
		this.workQueue = workQueue;
	}

	public void setResultQueue(Queue<R> resultQueue) {
		this.resultQueue = resultQueue;
	}

	/**
	 * 留给子类实现的方法
	 * 
	 * @param input
	 * @return
	 */
	public abstract R handle(T t);

	@Override
	public void run() {
		while (workQueue.size() != 0) {
			T input = workQueue.poll();
			if (input == null)
				break;
			R re = handle(input);
			resultQueue.add(re);
		}
	}
}