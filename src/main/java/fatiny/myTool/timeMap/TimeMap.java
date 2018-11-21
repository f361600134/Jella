package fatiny.myTool.timeMap;

import java.util.HashMap;
import java.util.Map;

/**
 * TimeMap  有延迟清空时间, 超过指定时间后, 清空所有的数据. 
 * 可以做游戏里面的物品倒计时拥有, 则需要单独拥有倒计时.
 * 也可以做成number形式的物品背包, 叠加数量, 数量不足时, 移除物品
 * @author Jeremy
 * @param <K>
 * @param <V> number 类型, 整型
 * @param <T>
 */
public class TimeMap<K, V>{
	
	//延迟时间
	private long delayTime;
	
	//下一次延迟时间的时间
	private long nextResetTime;
	
	//缓存数据
	private Map<K, IAssist> map;
	
	/**
	 * @param delayTime 毫秒(ms)
	 */
	public TimeMap(long delayTime) {
		this.delayTime = delayTime;
		this.nextResetTime = System.currentTimeMillis() + delayTime;
		this.map = new HashMap<K, IAssist>();
	}

	/**
	 * @param delayTime 毫秒(ms)
	 */
	public TimeMap(long delayTime, Map<K, IAssist> map) {
		this.nextResetTime = System.currentTimeMillis() + delayTime;
		this.map = map;
	}
	
	/**
	 * 如果延迟重置时间大于当前时间, 清空数据
	 */
	public void put(K key, IAssist assist) {
		//不符合时间机制, 直接清数据
		if (checkTime()) {
			map.put(key, assist);
		}else{
			//符合时间机制, 判断有无此数据, 有则合并. 无则重新添加
			if (map.containsKey(key)) {
				IAssist ass = map.get(key);
				ass.addAValue(assist.getAValue());
				map.put(key,  ass);
			}else{
				map.put(key, assist);
			}
		}
	}
	
	public IAssist get(K key){
		checkTime();
		return map.get(key);
	}
	
	/**
	 * 判断数据的有效性
	 * @return
	 */
	private boolean checkTime(){
		//不符合时间机制, 直接清数据
		if (nextResetTime < System.currentTimeMillis()) {
			map.clear();
			//计算下一次重置时间
			nextResetTime = System.currentTimeMillis() + delayTime;
			return true;
		}
		return false;
	}
	
	/**
	 * 重置数据
	 */
	public void clear(){
		delayTime = 0;
		nextResetTime = 0;
		map.clear();
	}
	
}
