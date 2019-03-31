package fatiny.myTool.sortMap.newer1;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.junit.Test;

import com.google.common.collect.Maps;

/**
 * @author Jeremy Feng
 *
 */
public class TestLeader {
	
	/**
	 * 对100000条数据进行测试,put时间
	 */
	@Test
	public void put(){
		long timestamp = System.currentTimeMillis();
		Leaderboard<Long, ISorter<Long>> leaderboard = 
				new Leaderboard<Long, ISorter<Long>>(new RankComparator(), Maps.newConcurrentMap());;
		leaderboard.setMaximum(5);
		RankInfo rankInfo = null;
		
		long startTime = System.currentTimeMillis();
		
		for (int i = 1; i < 10 ; i++) {
			rankInfo = new RankInfo((long)(i+1), "a"+i, i, timestamp+i); //+1
			leaderboard.put(rankInfo.getPlayerId(), rankInfo);
		}
		System.out.println(leaderboard.size());
		System.out.println("时间消耗:"+ (System.currentTimeMillis() - startTime));
	}
	
	/**
	 * 对100000条数据进行测试,putIfAbove时间91ms
	 */
	@Test
	public void putIfAbove(){
		long timestamp = System.currentTimeMillis();
		Leaderboard<Long, ISorter<Long>> leaderboard = 
				new Leaderboard<Long, ISorter<Long>>(new RankComparator());;
		leaderboard.setMaximum(100000);
		RankInfo rankInfo = null;
		
		long startTime = System.currentTimeMillis();
		for (int i = 10000000; i > 1; i--) {
			rankInfo = new RankInfo((long)(i+1), "a"+i, i, timestamp+i); //+1
			leaderboard.putIfAbove(rankInfo.getPlayerId(), rankInfo);
		}
		System.out.println(leaderboard.size());
		System.out.println(System.currentTimeMillis() - startTime);
		
	}
	
	/**
	 * 对100000条数据进行测试,put时间
	 */
	@Test
	public void putAll(){
		new HashSet<>();
		long timestamp = System.currentTimeMillis();
		Map<Long, ISorter<Long>> map = new HashMap<Long, ISorter<Long>>();
		RankInfo rankInfo = null;
		
		long startTime = System.currentTimeMillis();
		
		for (int i = 1; i <= 100000 ; i++) {
			rankInfo = new RankInfo((long)(i), "a"+i, i, timestamp+i); //+1
			map.put(rankInfo.getPlayerId(), rankInfo);
		}
		
		System.out.println("入库据使用"+(System.currentTimeMillis() - startTime));
		Leaderboard<Long, ISorter<Long>> leaderboard = new Leaderboard<Long, ISorter<Long>>(new RankComparator());;
		leaderboard.setMaximum(100);
		leaderboard.putAll(map);
		
		System.out.println(leaderboard.size());
		System.out.println("最后使用"+(System.currentTimeMillis() - startTime));
		
	}
	
	@Test
	public void putAll2(){
		Map<Long, ISorter<Long>> map = new HashMap<Long, ISorter<Long>>();
		long timestamp = System.currentTimeMillis();
		RankInfo rankInfo = null;
		for (int i = 1; i <= 1000000 ; i++) {
			rankInfo = new RankInfo((long)(i), "a"+i, i, timestamp+i); //+1
			map.put(rankInfo.getPlayerId(), rankInfo);
		}
		
		long startTime = System.currentTimeMillis();
		Leaderboard<Long, ISorter<Long>> leaderboard = new Leaderboard<Long, ISorter<Long>>(new RankComparator());;
		leaderboard.setMaximum(1000000);
		leaderboard.putAll(map);
		System.out.println("排序用时"+(System.currentTimeMillis() - startTime));
	}
	
	@Test
	public void headSet(){
		Map<Long, ISorter<Long>> map = new HashMap<Long, ISorter<Long>>();
		long timestamp = System.currentTimeMillis();
		RankInfo rankInfo = null;
		for (int i = 1; i <= 1000 ; i++) {
			rankInfo = new RankInfo((long)(i), "a"+i, i, timestamp+i); //+1
			map.put(rankInfo.getPlayerId(), rankInfo);
		}
		Leaderboard<Long, ISorter<Long>> leaderboard = new Leaderboard<Long, ISorter<Long>>(new RankComparator());;
		leaderboard.putAll(map);
		
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < 100; i++) {
			ISorter<Long> sorter = leaderboard.getRankInfo(500);
			leaderboard.headSet(sorter);
		}
		System.out.println("查询用时"+(System.currentTimeMillis() - startTime));
	}
	
	
	@Test
	public void subRank(){
		Map<Long, ISorter<Long>> map = new HashMap<Long, ISorter<Long>>();
		long timestamp = System.currentTimeMillis();
		RankInfo rankInfo = null;
		for (int i = 1; i <= 10 ; i++) {
			rankInfo = new RankInfo((long)(i), "a"+i, i, timestamp+i); //+1
			map.put(rankInfo.getPlayerId(), rankInfo);
		}
		Leaderboard<Long, ISorter<Long>> leaderboard = new Leaderboard<Long, ISorter<Long>>(new RankComparator());;
		leaderboard.putAll(map);
		
		long startTime = System.currentTimeMillis();
		Collection<ISorter<Long>> result = leaderboard.subRankInfo(2, 20);
		System.out.println("result:"+ result.size());
		System.out.println("查询用时"+(System.currentTimeMillis() - startTime));
	}
	
}
