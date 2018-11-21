package fatiny.myTool.rank.test;

import java.util.Random;

import fatiny.myTool.rank.test.common.GameEventBus;
import fatiny.myTool.rank.test.event.RankChangeEvent;

/**
 * @author Jeremy Feng
 *
 */
public class Player implements Runnable {
	
	private Long playerId;
	private String name;
	private int value;
	
	
	public Player(){}
	public Player(Long playerId, String name, int value) {
		super();
		this.playerId = playerId;
		this.name = name;
		this.value = value;
	}

	public Long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(Long playerId) {
		this.playerId = playerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public static void main(String[] args) {
		Random random = new Random();
		System.out.println(random.nextInt(100));
	}

	/**
	 * 对于业务层来说, 不能直接处理这层业务, 需要交给其他线程去处理.
	 * 1. 写一个自己的队列, 然后交给那个队列去处理
	 * 2. 使用EventBus事件触发机制, 交给EventBus去处理
	 */
	@Override
	public void run() {
		// 这里不能直接获取资源处理业务, 因为线程会把资源占有, 这条线程不销毁, 其他线程就会挂起永久等待
//		Random random = new Random();
		for (int i = 0; i < 1000; i++) {
			//int value = random.nextInt(100);
			int value = this.value + 10;
			this.setValue(value);
			GameEventBus.instance().post(new RankChangeEvent(this));
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
	
}
