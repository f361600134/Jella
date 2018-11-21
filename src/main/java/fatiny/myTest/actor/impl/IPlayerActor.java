package fatiny.myTest.actor.impl;

import fatiny.myTest.actor.PlayerContext;

/**
 * IPlayerActor
 *
 * @author huachp
 */
public interface IPlayerActor {
	
	
	/**
	 * 发事件
	 * 
	 * @param events
	 */
	void postEvent(Object... events);
	
	void updateData(PlayerContext otherCtx);
	
	/**
	 * 初始化上下文
	 * 
	 * @param playerCtx
	 */
	boolean initContext(PlayerContext playerCtx);
	
}
