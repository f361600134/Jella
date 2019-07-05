package fatiny.myTool.sortMap.newer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * @author Jeremy Feng
 *
 */
public class TestLeader {
	
	@Test
	public void test(){
		long timestamp = System.currentTimeMillis();
		Leaderboard<Long, ISorter> rankSorter = new Leaderboard<Long, ISorter>(new RankComparator());;
		rankSorter.setMaximum(10);
		
		RankInfo rankInfo1  = new RankInfo(1L, "a", 10, timestamp+1); //+1
		RankInfo rankInfo2  = new RankInfo(2L, "b", 20, timestamp+2); //+2
		RankInfo rankInfo3  = new RankInfo(3L, "c", 30, timestamp+3); //+3
		RankInfo rankInfo4  = new RankInfo(4L, "d", 40, timestamp+4); //+4
		RankInfo rankInfo5  = new RankInfo(5L, "e", 50, timestamp+5); //+5
		RankInfo rankInfo6  = new RankInfo(6L, "f", 60, timestamp+6); //+6
		RankInfo rankInfo7  = new RankInfo(7L, "g", 70, timestamp+7); //+7
		RankInfo rankInfo8  = new RankInfo(8L, "h", 80, timestamp+8); //+8
		RankInfo rankInfo9  = new RankInfo(9L, "e", 90, timestamp+9); //+9
		RankInfo rankInfo10  = new RankInfo(10L, "f", 100, timestamp+10); //+10
		RankInfo rankInfo11  = new RankInfo(11L, "g", 100, timestamp+11); //+10
		
		rankSorter.put(null, rankInfo1);
		
		rankSorter.put(rankInfo1.getPlayerId(), rankInfo1);
		rankSorter.put(rankInfo2.getPlayerId(), rankInfo2);
		rankSorter.put(rankInfo3.getPlayerId(), rankInfo3);
		rankSorter.put(rankInfo4.getPlayerId(), rankInfo4);
		rankSorter.put(rankInfo5.getPlayerId(), rankInfo5);
		rankSorter.put(rankInfo6.getPlayerId(), rankInfo6);
		rankSorter.put(rankInfo7.getPlayerId(), rankInfo7);
		rankSorter.put(rankInfo8.getPlayerId(), rankInfo8);
		rankSorter.put(rankInfo9.getPlayerId(), rankInfo9);
		boolean bool = rankSorter.putIfAbove(rankInfo10.getPlayerId(), rankInfo10);
		rankSorter.putIfAbove(rankInfo11.getPlayerId(), rankInfo11);
		
//		System.out.println(bool);
		System.out.println(rankSorter);
		
	}
	
	/**
	 * 对100000条数据进行测试,put时间
	 */
	@Test
	public void put(){
		
		long timestamp = System.currentTimeMillis();
		Leaderboard<Long, ISorter> leaderboard = new Leaderboard<Long, ISorter>(new RankComparator());;
		//leaderboard.setMaximum(100);
		RankInfo rankInfo = null;
		
		long startTime = System.currentTimeMillis();
		
		for (int i = 10; i > 1 ; i--) {
			rankInfo = new RankInfo((long)(i+1), "a"+i, 1, timestamp); //+1
			leaderboard.put(rankInfo.getPlayerId(), rankInfo);
		}
		System.out.println(leaderboard.size());
		System.out.println(System.currentTimeMillis() - startTime);
		
	}
	
	/**
	 * 对100000条数据进行测试,putIfAbove时间91
	 */
	@Test
	public void putIfAbove(){
		
		long timestamp = System.currentTimeMillis();
		Leaderboard<Long, ISorter> leaderboard = new Leaderboard<Long, ISorter>(new RankComparator());;
		//leaderboard.setMaximum(100);
		RankInfo rankInfo = null;
		
		long startTime = System.currentTimeMillis();
		
		for (int i = 1000000; i > 1; i--) {
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
	public void putAllItem(){
		
		long timestamp = System.currentTimeMillis();
		Map<Long, ISorter> map = new HashMap<Long, ISorter>();;
		RankInfo rankInfo = null;
		
		long startTime = System.currentTimeMillis();
		
		for (int i = 1000000; i > 1 ; i--) {
			rankInfo = new RankInfo((long)(i+1), "a"+i, i, timestamp+i); //+1
			map.put(rankInfo.getPlayerId(), rankInfo);
		}
		
		System.out.println("入数据使用"+(System.currentTimeMillis() - startTime));
		
		Leaderboard<Long, ISorter> leaderboard = new Leaderboard<Long, ISorter>(new RankComparator());;
		leaderboard.setMaximum(1000000);
		leaderboard.putAll(map);
		
		System.out.println(leaderboard.size());
		System.out.println("最后使用"+(System.currentTimeMillis() - startTime));
		
	}
	
	/**
	 *  分组A方法
	 *  使用下表的方,来分组
	 */
	@Test
	public void groupA(){
		long timestamp = System.currentTimeMillis();
		Leaderboard<Long, ISorter> rankSorter = new Leaderboard<Long, ISorter>(new RankComparator());;
		rankSorter.setMaximum(10);
		
		RankInfo rankInfo1  = new RankInfo(1L, "a", 10, timestamp+1); //+1
		RankInfo rankInfo2  = new RankInfo(2L, "b", 20, timestamp+2); //+2
		RankInfo rankInfo3  = new RankInfo(3L, "c", 30, timestamp+3); //+3
		RankInfo rankInfo4  = new RankInfo(4L, "d", 40, timestamp+4); //+4
		RankInfo rankInfo5  = new RankInfo(5L, "e", 50, timestamp+5); //+5
		RankInfo rankInfo6  = new RankInfo(6L, "f", 60, timestamp+6); //+6
		RankInfo rankInfo7  = new RankInfo(7L, "g", 70, timestamp+7); //+7
		RankInfo rankInfo8  = new RankInfo(8L, "h", 80, timestamp+8); //+8
		RankInfo rankInfo9  = new RankInfo(9L, "e", 90, timestamp+9); //+9
		RankInfo rankInfo10  = new RankInfo(10L, "f", 100, timestamp+10); //+10
		
		rankSorter.put(rankInfo1.getPlayerId(), rankInfo1);
		rankSorter.put(rankInfo2.getPlayerId(), rankInfo2);
		rankSorter.put(rankInfo3.getPlayerId(), rankInfo3);
		rankSorter.put(rankInfo4.getPlayerId(), rankInfo4);
		rankSorter.put(rankInfo5.getPlayerId(), rankInfo5);
		rankSorter.put(rankInfo6.getPlayerId(), rankInfo6);
		rankSorter.put(rankInfo7.getPlayerId(), rankInfo7);
		rankSorter.put(rankInfo8.getPlayerId(), rankInfo8);
		rankSorter.put(rankInfo9.getPlayerId(), rankInfo9);
		rankSorter.put(rankInfo10.getPlayerId(), rankInfo10);
		
		long start = System.currentTimeMillis();
		Collection<ISorter> result = new ArrayList<>();
		
		int first =1, last = 3;
		int size = rankSorter.size();
		while (first < size){
			result = rankSorter.subRankInfo(first, last);
    		if (size - last == 4) {
    			first+= 3;
    			last += 2;
			}else if (size - last == 2) {
				first+=	2;
				last += 2;
			}else{
				first+=	3;
				last += 3;
			}
			System.out.println(result);
		}
		
		System.out.println("Method A:"+(System.currentTimeMillis() - start));
	}
	
	/**
	 * 获取数据,然后移除数据
	 */
	@Test
	public void groupB(){
		long timestamp = System.currentTimeMillis();
		Leaderboard<Long, ISorter> rankSorter = new Leaderboard<Long, ISorter>(new RankComparator());;
		rankSorter.setMaximum(10);
		
		RankInfo rankInfo1  = new RankInfo(1L, "a", 10, timestamp+1); //+1
		RankInfo rankInfo2  = new RankInfo(2L, "b", 20, timestamp+2); //+2
		RankInfo rankInfo3  = new RankInfo(3L, "c", 30, timestamp+3); //+3
		RankInfo rankInfo4  = new RankInfo(4L, "d", 40, timestamp+4); //+4
		RankInfo rankInfo5  = new RankInfo(5L, "e", 50, timestamp+5); //+5
		RankInfo rankInfo6  = new RankInfo(6L, "f", 60, timestamp+6); //+6
		RankInfo rankInfo7  = new RankInfo(7L, "g", 70, timestamp+7); //+7
		RankInfo rankInfo8  = new RankInfo(8L, "h", 80, timestamp+8); //+8
		RankInfo rankInfo9  = new RankInfo(9L, "e", 90, timestamp+9); //+9
		RankInfo rankInfo10  = new RankInfo(10L, "f", 100, timestamp+10); //+10
		
		rankSorter.put(rankInfo1.getPlayerId(), rankInfo1);
		rankSorter.put(rankInfo2.getPlayerId(), rankInfo2);
		rankSorter.put(rankInfo3.getPlayerId(), rankInfo3);
		rankSorter.put(rankInfo4.getPlayerId(), rankInfo4);
		rankSorter.put(rankInfo5.getPlayerId(), rankInfo5);
		rankSorter.put(rankInfo6.getPlayerId(), rankInfo6);
		rankSorter.put(rankInfo7.getPlayerId(), rankInfo7);
		rankSorter.put(rankInfo8.getPlayerId(), rankInfo8);
		rankSorter.put(rankInfo9.getPlayerId(), rankInfo9);
		rankSorter.put(rankInfo10.getPlayerId(), rankInfo10);
		
		long start = System.currentTimeMillis();
		
		Collection<ISorter> result = new ArrayList<>();
		int first =1, last = 3;
		while (rankSorter.size() > 0){
    		if (rankSorter.size() == 4) {
    			last = 2;
			}
    		result = rankSorter.subRankInfo(first, last);
    		for (ISorter iSorter : result) {
    			rankSorter.remove(iSorter.getId());
			}
			System.out.println(result);
		}
		System.out.println("Method B:"+(System.currentTimeMillis() - start));
	}
	
	@Test
	public void groupTest(){
		
		long timestamp = System.currentTimeMillis();
		Map<Long, ISorter> map = new HashMap<Long, ISorter>();;
		RankInfo rankInfo = null;
		
		for (int i = 12; i > 1 ; i--) {
			rankInfo = new RankInfo((long)(i+1), "a"+i, i, timestamp+i); //+1
			map.put(rankInfo.getPlayerId(), rankInfo);
		}

		Leaderboard<Long, ISorter> leaderboard = new Leaderboard<Long, ISorter>(new RankComparator());;
		//leaderboard.setMaximum(100);
		leaderboard.putAll(map);
		
		System.out.println(leaderboard.size());
		
		methodA(leaderboard);
		methodB(leaderboard);
	}
	
	/**
	 * A方法. 这种方法问题很大
	 * @param rankSorter
	 */
	public void methodA(Leaderboard<Long, ISorter> rankSorter){
		long start = System.currentTimeMillis();
		Collection<ISorter> result = new ArrayList<>();
		
		int first =1, last = 3;
		int size = rankSorter.size();
		while (first < size){
			result = rankSorter.subRankInfo(first, last);
    		if (size - last == 4) {
    			first+= 3;
    			last += 2;
			}else if (size - last == 2) {
				first+=	2;
				last += 2;
			}else{
				first+=	3;
				last += 3;
			}
			System.out.println(result);
		}
		
		System.out.println("Method A:"+(System.currentTimeMillis() - start));
	}
	
	/**
	 * A方法
	 * @param rankSorter
	 */
	public void methodB(Leaderboard<Long, ISorter> rankSorter){
		long start = System.currentTimeMillis();
		
		Collection<ISorter> result = new ArrayList<>();
		int first =1, last = 3;
		while (rankSorter.size() > 0){
    		if (rankSorter.size() == 4) {
    			last = 2;
			}
    		result = rankSorter.subRankInfo(first, last);
    		for (ISorter iSorter : result) {
    			rankSorter.remove(iSorter.getId());
			}
			System.out.println(result);
		}
		System.out.println("Method B:"+(System.currentTimeMillis() - start));
	}
	
	
	
}
