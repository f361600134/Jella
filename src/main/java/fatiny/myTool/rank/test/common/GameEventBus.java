package fatiny.myTool.rank.test.common;

import java.util.Collection;

import com.google.common.eventbus.EventBus;

/**
 * 游戏事件总线
 * @author huangjc
 */
public class GameEventBus {
	private static GameEventBus gameEventBus = new GameEventBus();
	private EventBus eventBus = new EventBus();
	
	private GameEventBus(){
	}
	
	public static GameEventBus instance(){
		return gameEventBus;
	}

	/**
	 * 注册观察者相关的订阅事件
	 * @param object
	 */
	public void register(Object object) {
		eventBus.register(object);
	}
	
	public void register(Collection<Class<?>> classes) {
		for (Class<?> cls : classes) {
			try {
				Object o = cls.newInstance();
				register(o);
			} catch (Exception e) {
				System.out.println("注册观察者过程出现异常");
			}
		}
	}
	
	/**
	 * 发送事件
	 * @param event
	 */
	public void post(Object event) {
		eventBus.post(event);
	}
}
