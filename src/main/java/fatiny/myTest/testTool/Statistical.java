package fatiny.myTest.testTool;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Statistical {
	
	public static Logger logger = LoggerFactory.getLogger(Statistical.class);
	
	/**
	 * 添加一条记录
	 * @param rankName
	 * @param key
	 * @param score
	 * @param isForceFlush
	 *            -- 是否强制刷新缓存--删除原有榜，重新添加（需要用的地方如：原本在排行榜上，突然掉出了排行榜，用于排名会下降的榜）
	 * @return 返回key在排行榜中的排名，如果不在排行榜中则返回-1
	 */
	public static HashMap<String, Long> timeMap = new HashMap<String, Long>();
	public static String offer = "OFFER";
	public static String poll = "POLL";
	
	public static void statisticalTime(String op, long startTime){
		Long time = timeMap.get(op);
		long costTime = System.currentTimeMillis() - startTime;
		if(time == null){
			timeMap.put(op, costTime);
		}else{
			timeMap.put(op, (time + costTime));
		}
	}
	public static void print(int count){
		count = count == 0 ? 1: count;
		for (String key : timeMap.keySet()) {
			long totalTime = timeMap.get(key);
			logger.debug("操作:{} -> 花费总时间时间:{}, 平均:{}", key, totalTime, (totalTime/count));
		}
	}
	

}
