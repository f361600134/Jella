package fatiny.myTool.sortMap.newer1;

import java.util.Map;

public interface ILeaderboard<K, V> extends Map<K, V>{
	
	/**
	 * 获取指定对象排名
	 * @param v
	 * @return  
	 * @return Integer  
	 * @date 2019年3月25日下午2:27:52
	 */
	public Integer getRanking(V v);
	
	/**
	 * 获取第一个玩家
	 * @return  
	 * @return V  
	 * @date 2019年3月25日下午2:27:26
	 */
	public V getFirst();
	
	/**
	 * 获取第一个玩家
	 * @return  
	 * @return V  
	 * @date 2019年3月25日下午2:27:26
	 */
	public V getLast();
	
	/**
	 * 根据指定排名获取到对象
	 * @return  
	 * @return V  
	 * @date 2019年3月25日下午2:28:59
	 */
	public V getValueByIndex(int index);
	
}
