package fatiny.myTool.Jredis.newer;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fatiny.myTool.Jredis.older.rank.RankInfo;
import fatiny.myTool.Jredis.older.rank.RankSorter;
import fatiny.myTool.Jredis.older.util.Statistical;

public class RankSorterTest {
	
	private Logger logger = LoggerFactory.getLogger(RankSorterTest.class);
	
	private static final String redisIP= "127.0.0.1";
	private static final int port = 9999;
	private static RankSorter rankSorter = null;
	public static String rankName = "Redis";
	
	@Before
	public void connect(){
		//获取redis连接
		rankSorter = new RankSorter(redisIP, port, 300, 200, "CYByeApRGnR*@68");
		rankSorter.init(rankName, 100);
	}
	
	@Test
	public void add(){
		long startTime = System.currentTimeMillis();
		RankInfo rankInfo = null;
//		for (int i = 10000; i > 1 ; i--) {
		for (int i = 1; i <= 1000 ; i++) {
			rankInfo = new RankInfo((long)(i), "a"+i, i, startTime+i); //+1
			rankSorter.add(rankName, rankInfo);
		}
		logger.debug("消耗时间:{}", (System.currentTimeMillis() - startTime));
		Statistical.print();
		rankSorter.size(rankName);
	}
	
	@Test
	public void addAll(){
		
	}
	
	
//	
//	/**
//	 * 对100000条数据进行测试,put时间
//	 */
//	@Test
//	public void put(){
//		long startTime = System.currentTimeMillis();
//		for (int i = 1; i < 100 ; i++) {
//			redisRank.put(rankName, "a"+i, (long)(i), false);
//		}
//		logger.debug("排行榜用时: {}ms", (System.currentTimeMillis() - startTime));
//		logger.debug("排行榜数量:{}", redisRank.getRankNum(rankName));
//		
//		redisRank.zrange(rankName);
//		redisRank.zrangeWithScores(rankName);
//		
//		System.out.println();
//		redisRank.zrange1(rankName);
////		redisRank.zrangeWithScores1(rankName);
//		
////		List<RankScoreObject> list = redisRank.range(rankName, 0, 100);
////		for (RankScoreObject r : list) {
////			logger.debug("id:{}, score:{}, rank:{}", r.getKey(), r.getScore(), r.getRank());
////		}
//		RedisRank.print();
//	}
//	
//	/**
//	 * 对100000条数据进行测试,put时间
//	 */
//	@Test
//	public void put2(){
//		long startTime = System.currentTimeMillis();
//		for (int i = 1; i < 10 ; i++) {
//			redisRank.addForTest(rankName, "a"+1, (long)(i+1), false);
//		}
//		logger.debug("排行榜用时: {}ms", (System.currentTimeMillis() - startTime));
//		logger.debug("排行榜数量:{}", redisRank.getRankNum(rankName));
//		
////		List<RankScoreObject> list = redisRank.range(rankName, 0, 100);
////		for (RankScoreObject r : list) {
////			logger.debug("id:{}, score:{}, rank:{}", r.getKey(), r.getScore(), r.getRank());
////		}
//		RedisRank.print();
//	}
//	
//	/**
//	 * 对100000条数据进行测试,批量存储时间
//	 */
//	@Test
//	public void putAll(){
//		Map<String, Double> map = new HashMap<String, Double>();
//		for (int i = 1; i < 100000 ; i++) {
//			map.put("a"+i, (double) (i+1));
//		}
//		
//		long startTime = System.currentTimeMillis();
//		redisRank.addAll(rankName, map);
//		logger.debug("排行榜用时: {}ms", (System.currentTimeMillis() - startTime));
//		logger.debug("排行榜数量:{}", redisRank.getRankNum(rankName));
//		
//		List<RankScoreObject> list = redisRank.range(rankName, 0, 100);
//		for (RankScoreObject r : list) {
//			logger.debug("id:{}, score:{}, rank:{}", r.getKey(), r.getScore(), r.getRank());
//		}
//		
//		RedisRank.print();
//	}
//
//	/**
//	 * 测试
//	 * 原始10条数据, 并发处理排行榜数据修改. 高并发之后,数据是否完整,或是否出现脏数据
//	 */
//	@Test
//	public void testThread(){
//		//初始化十条数据
//		for (int i = 0; i < 10 ; i++) {
//			redisRank.add(rankName, "a"+i, (long)(i), false);
//		}
//		redisRank.refresh(rankName);
//		
//		//玩家数据
//		Player Jeremy = new Player(1001L, "Jeremy", 100, redisRank);
//		Player Lora = new Player(1002L, "Lora", 100, redisRank);
//		List<Player> players = Lists.newArrayList(Jeremy, Lora);
//		
//		Thread thread = null;
//		for (Player player : players) {
//			thread = new Thread(player);  
//			thread.start();
////			try {
////				thread.join();
////			} catch (InterruptedException e) {
////				e.printStackTrace();
////			}
//		}
//		
//	}
//	
//	
//	public static void main(String[] args) {
//		//获取redis连接
//		redisRank = new RedisRank(redisIP, port, 300, 200, "CYByeApRGnR*@68");
//		redisRank.initRank(rankName, 10);
//		
//		//初始化十条数据
//		for (int i = 0; i < 10 ; i++) {
//			redisRank.add(rankName, "a"+i, (long)(i), false);
//		}
//		redisRank.refresh(rankName);
//		
//		//玩家数据
//		Player Jeremy = new Player(1001L, "Jeremy", 100, redisRank);
//		Player Lora = new Player(1002L, "Lora", 100, redisRank);
//		Player Jake = new Player(1003L, "Jake", 100, redisRank);
//		Player Li = new Player(1004L, "Li", 100, redisRank);
//		List<Player> players = Lists.newArrayList(Jeremy, Lora , Jake, Li);
//		
//		Thread thread = null;
//		for (Player player : players) {
//			thread = new Thread(player);  
//			thread.start();
////			try {
////				thread.join();
////			} catch (Exception e) {
////				e.printStackTrace();
////			}
//		}
//	}
	
	
}
