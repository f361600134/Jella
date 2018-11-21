package fatiny.myTool.timeMap;

import java.util.HashMap;
import java.util.Map;

/**
 * TimeMap  有延迟清空时间, 超过指定时间后, 清空所有的数据. 
 * @author Jeremy
 * @param <K>
 * @param <V>
 */
public class TimerMap{
	
	//延迟时间
	private long delayTime;
	
	//下一次延迟时间的时间
	private long nextResetTime;
	
	//缓存数据
	private Map<Integer, IAssist> map;
	
	/**
	 * @param delayTime 毫秒(ms)
	 */
	public TimerMap(long delayTime) {
		this.delayTime = delayTime;
		this.nextResetTime = System.currentTimeMillis() + delayTime;
		this.map = new HashMap<Integer, IAssist>();
	}

	/**
	 * @param delayTime 毫秒(ms)
	 */
	public TimerMap(long delayTime, Map<Integer, IAssist> map) {
		this.nextResetTime = System.currentTimeMillis() + delayTime;
		this.map = map;
	}
	
	/**
	 * 如果延迟重置时间大于当前时间, 清空数据
	 */
	public void put(Integer key, IAssist record) {
		//不符合时间机制, 直接清数据
		if (nextResetTime < System.currentTimeMillis()) {
			map.clear();
			map.put(key, record);
			//计算下一次重置时间
			nextResetTime = System.currentTimeMillis() + delayTime;
		}else{
			//符合时间机制, 判断有无此数据, 有则合并. 无则重新添加
//			if (map.containsKey(key)) {
//				AlarmRecordPo ass = map.get(key);
//				ass.setUsenum(ass.getUsenum() + record.getUsenum());
//				map.put(key,  ass);
//			}else{
//				map.put(key, record);
//			}
		}
	}
}
