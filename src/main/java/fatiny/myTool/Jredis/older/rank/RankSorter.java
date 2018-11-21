package fatiny.myTool.Jredis.older.rank;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.Tuple;
import fatiny.myTool.Jredis.older.tool.RedisClient;
import fatiny.myTool.Jredis.older.util.Statistical;

/**
 * redis排行榜
 * 
 * rankSorter 只用于做排行功能, 如果需要多个排行榜, 则从外部去生成.
 * @author sq
 */
public class RankSorter extends RedisClient {
	
	private static Logger logger = LoggerFactory.getLogger(RankSorter.class);
	
	/**
	 * 记录排行榜最大size
	 */
	private Map<String, RankDetail> ranks;

	/**
	 * 构造器, 设置连接信息
	 * @param ip
	 * @param port
	 * @param maxTotal
	 * @param maxIdle
	 * @param password
	 */
	public RankSorter(String ip, int port, int maxTotal, int maxIdle,
			String password) {
		super(ip, port, maxTotal, maxIdle, password);
		ranks = new HashMap<>();
	}
	
	public void init(String rankName, int limit) {
		ShardedJedis jedis = null;
		try {
			jedis = getConnect();
			RankDetail rankDetail = new RankDetail(limit);
			//Set<Tuple> set = jedis.zrangeWithScores(rankName, 0, -1);
			Set<Tuple> set = jedis.zrevrangeWithScores(rankName, 0, -1);
			//int rank = 0;
			RankInfo rankInfo = null;
			for (Tuple tuple : set) {
				rankInfo = new RankInfo();
				rankInfo.setPlayerId(Long.valueOf(tuple.getElement()));
				rankInfo.setValue(tuple.getScore());
				//rankInfo.setRank(++rank);
				rankDetail.put(rankInfo);
				if (rankDetail.getMinScore() == 0 || tuple.getScore() < rankDetail.getMinScore()) {
					rankDetail.setMinScore(tuple.getScore());
				}
			}
			ranks.put(rankName, rankDetail);
		} catch (RuntimeException e) {
			logger.error("redis exception", e);
			throw e;
		} 
	}
	
	/**
	 * 添加新数据时
	 * @param sorter
	 */
	public void add(String rankName, ISorter sorter){
		ShardedJedis jedis = null;
		RankDetail rankDetail = ranks.get(rankName);
//		long currentTime = System.currentTimeMillis();
		try {
			jedis = getConnect();
//			Statistical.statisticalTime(Statistical.getConnect, currentTime);
			//先判断排行榜是否满了, 满了则移除分值最小的那个
			if (rankDetail.isFull()) {
				//判断是否可以加入到排行榜
				if ((Double) sorter.getSortValue() > rankDetail.getMinScore()) {
					jedis.zadd(rankName, sorter.getSortValue(), sorter.getSortId());
					rankDetail.put(sorter);
//					updateRank(jedis, sorter, rankDetail, rankName);
				}
			}else{
				//排行榜没满, 直接插入
				jedis.zadd(rankName, sorter.getSortValue(), sorter.getSortId());
				rankDetail.put(sorter);
//				updateRank(jedis, sorter, rankDetail, rankName);
			}
//			Statistical.statisticalTime(Statistical.zadd, currentTime);
			
			removeOverflow(jedis, rankDetail, rankName);
//			logger.debug("put, rankDetail:{}, set:{}", rankDetail.getMap(), jedis.zrange(rankName, 0, -1));
			
//			Statistical.statisticalTime(Statistical.zremove, currentTime);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			returnConnect(jedis);
//			Statistical.statisticalTime(Statistical.returnConnect, currentTime);
		}
	}
	
	/**
	 * 溢出则移除
	 * redis中是顺序排序, 当超过指定数量时, 需要移除超过的数量.
	 * 需要移除的数量 = 当前数量 - 数量上限. 然后把数量转换成下标.(n-1)
	 */
	private void removeIfoverflow(ShardedJedis jedis, RankDetail rankDetail, String rankName){
		if (rankDetail.isFlow()) {
			//溢出溢出部分数据下标起始
			int start = 0;
			int end = rankDetail.getSpillSize() - 1;
			 
			//移除本地缓存数据
			Set<Tuple> set = jedis.zrangeWithScores(rankName, start, end);
			for (Tuple tuple : set) {
				rankDetail.remove(tuple.getElement());
				if (rankDetail.getMinScore() == 0 || tuple.getScore() < rankDetail.getMinScore()) {
					rankDetail.setMinScore(tuple.getScore());
				}
			}
			//移除redis缓存数据
			jedis.zremrangeByRank(rankName, start, end);
		}
	}
	
	/**
	 * 溢出则移除
	 * 单个溢出删除
	 */
	private void removeOverflow(ShardedJedis jedis, RankDetail rankDetail, String rankName){
		if (rankDetail.isFlow()) {
			//溢出溢出部分数据下标起始
			Tuple tuple = getLast(jedis, rankName);
			rankDetail.remove(tuple.getElement());
			rankDetail.setMinScore(tuple.getScore());
			jedis.zrem(rankName, tuple.getElement());
		}
	}
	
	/**
	 * 更新Map中的缓存
	 * 事实上, 保存的map用处相对比较小了, 因为并没有排名在内部.
	 * 所以实质的用处并不大, 留着方法先
	 */
//	private void updateRank(ShardedJedis jedis, ISorter sorter, RankDetail rankDetail, String rankName){
//		RankInfo rankInfo = (RankInfo) sorter;
//		long rank = jedis.zrevrank(rankName, sorter.getSortId());
//		rankInfo.setRank((int)++rank);
//		rankDetail.put(rankInfo);
//	}
	
	/**
	 * 获取最后一名信息
	 * @param jedis
	 * @param rankName
	 * @return
	 */
	private Tuple getLast(ShardedJedis jedis, String rankName){
		Tuple result = null;
		Set<Tuple> set = jedis.zrangeWithScores(rankName, 0, 0);
		if (set != null && set.size() > 0) {
			result = set.iterator().next();
		}
		return result;
	}
	
	/**
	 * 批量插入
	 *
	 * @param rankName
	 * @param map
	 */
	public void addAll(String rankName, Map<String, Double> map) {
		Statistical.timeMap.clear();
		//long currentTime = System.currentTimeMillis();
		
		ShardedJedis jedis = null;
		RankDetail rankDetail = ranks.get(rankName);
		
		//先对需要批量添加的数据进行过滤
		Map<String, Double> scanMap = new HashMap<String, Double>();
		for (Entry<String, Double> entry : map.entrySet()) {
			if (entry.getValue() > rankDetail.getMinScore()) {
				scanMap.put(entry.getKey(), entry.getValue());
				//添加缓存数据
				rankDetail.put(new RankInfo(Long.valueOf(entry.getKey()), entry.getValue()));
			}
		}
//		Statistical.statisticalTime(Statistical.znew, currentTime);
		try {
			//是否可以加入数据
			int size = scanMap.size();
			if (size > 0) {
				jedis = getConnect();
//				Statistical.statisticalTime(Statistical.getConnect, currentTime);
				
				//添加数据
				jedis.zadd(rankName, scanMap);
//				Statistical.statisticalTime(Statistical.zadd, currentTime);
				
				removeIfoverflow(jedis, rankDetail, rankName);
//				Statistical.statisticalTime(Statistical.zremove, currentTime);
			}
		}catch (RuntimeException e) {
			returnExceptionConnection(jedis);
			logger.error("redis exception", e);
			throw e;
		} finally {
			returnConnect(jedis);
//			Statistical.statisticalTime(Statistical.returnConnect, currentTime);
//			Statistical.print();
		}
	}
	
	public boolean check() {
		ShardedJedis jedis = null;
		try {
			jedis = getConnect();
			jedis.get("check");
		} catch (Exception e) {
			return false;
		} finally {
			returnConnect(jedis);
		}
		return true;
	}

	/**
	 * 返回一段排行榜
	 * @param rankName
	 * @param start 起始点 >=0
	 * @param end 终点 >=start
	 * @return
	 */
	public Collection<ISorter> range(String rankName, int start, int end){
		ShardedJedis jedis = null;
		try {
			jedis = getConnect();
			//返回有序set
			Set<Tuple> rankSet = jedis.zrevrangeWithScores(rankName, start, end);
			Collection<ISorter> result = new ArrayList<ISorter>();

			int rank = 0;
			for (Tuple tuple : rankSet) {
				RankInfo info = new RankInfo();
				info.setPlayerId(Long.valueOf(tuple.getElement()));
				info.setValue(tuple.getScore());
				info.setRank(++rank);
				result.add(info);
			}
			return result;
		} catch (RuntimeException e) {
			returnExceptionConnection(jedis);
			logger.error("redis exception", e);
			throw e;
		} finally {
			returnConnect(jedis);
		}
	}

	/**
	 * 根据key，删除缓存
	 */
	public void remove(String rankName) {
		ShardedJedis jedis = null;
		try {
			jedis = getConnect();
			ranks.remove(rankName);
			jedis.del(rankName);
		} catch (RuntimeException e) {
			returnExceptionConnection(jedis);
			logger.error("redis exception", e);
			throw e;
		} finally {
			returnConnect(jedis);
		}
	}
	
	/**
	 * 根据key, 移除一个成员变量 
	 */
	public void remove(String rankName, String key) {
		ShardedJedis jedis = null;
		RankDetail rankDetail = ranks.get(rankName);
		try {
			jedis = getConnect();
			//数量固定, 所以当移除一个的时候, 缓存也需要移除一个数量
			jedis.zrem(rankName, key);
			rankDetail.remove(key);

		} catch (RuntimeException e) {
			returnExceptionConnection(jedis);
			logger.error("redis exception", e);
			throw e;
		} finally {
			returnConnect(jedis);
		}
	}

	/**
	 * 获取排行名次
	 * @param rankName
	 * @param key
	 * @return
	 */
	public long getRank(String rankName, String key) {
		ShardedJedis jedis = null;
		try {
			jedis = getConnect();
			//zrevrank是从高分到低分的排名，倒序的排名
			Long rank = jedis.zrevrank(rankName, key);
			if(rank == null) {
				return 0;
			}
			//redis的排行数据是从0开始的，所以需要加1
			return rank + 1;
		} catch (RuntimeException e) {
			returnExceptionConnection(jedis);
			logger.error("redis exception", e);
			throw e;
		} finally {
			returnConnect(jedis);
		}
	}
	
	/**
	 * 根据 key 获取 score
	 * @param rankName
	 * @param key 
	 * @return
	 */
	public Double getScore(String rankName, String key) {
		RankDetail rankDetail = ranks.get(rankName);
		ISorter sorter = rankDetail.get(key);
		return sorter == null ?  0 : sorter.getSortValue();
	}
	
	/**
	 * 因为RankInfo中的信息跟redis中的信息完全一致, 所以直接从rankInfo中获取数量
	 * @param rankName
	 * @return
	 */
	public long size(String rankName){
		RankDetail rankDetail = ranks.get(rankName);
		return rankDetail.size();
	}
	
}
