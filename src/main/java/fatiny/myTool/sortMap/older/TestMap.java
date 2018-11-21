package fatiny.myTool.sortMap.older;

import java.util.Collection;
import java.util.Comparator;

import org.junit.Test;

import fatiny.myTool.sortMap.common.SortedValueMap;

/**
 * @author Jeremy Feng
 *
 */
public class TestMap {
	
	public static void main(String[] args) {
		
		long timestamp = System.currentTimeMillis();
		RankSorter rankSorter = new RankSorter(new rankInfoComparator());;
		rankSorter.setMaximum(6);
		
		RankInfo rankInfo1  = new RankInfo(1L, "a", 10, timestamp+1); //+1
		RankInfo rankInfo2  = new RankInfo(2L, "b", 20, timestamp+2); //+2
		RankInfo rankInfo3  = new RankInfo(3L, "c", 30, timestamp+3); //+3
		RankInfo rankInfo4  = new RankInfo(4L, "d", 40, timestamp+4); //+4
		RankInfo rankInfo5  = new RankInfo(5L, "e", 50, timestamp+5); //+5
//		RankInfo rankInfo6  = new RankInfo(6L, "f", 60, timestamp+6); //+6
//		RankInfo rankInfo7  = new RankInfo(7L, "g", 70, timestamp+7); //+7
//		RankInfo rankInfo8  = new RankInfo(8L, "h", 80, timestamp+8); //+8
//		RankInfo rankInfo9  = new RankInfo(9L, "e", 90, timestamp+9); //+9
		
		rankSorter.put(rankInfo1.getPlayerId(), rankInfo1);
		rankSorter.put(rankInfo2.getPlayerId(), rankInfo2);
		rankSorter.put(rankInfo3.getPlayerId(), rankInfo3);
		rankSorter.put(rankInfo4.getPlayerId(), rankInfo4);
		rankSorter.put(rankInfo5.getPlayerId(), rankInfo5);
//		rankSorter.put(rankInfo6.getPlayerId(), rankInfo6);
//		rankSorter.put(rankInfo7.getPlayerId(), rankInfo7);
//		rankSorter.put(rankInfo8.getPlayerId(), rankInfo8);
//		rankSorter.put(rankInfo9.getPlayerId(), rankInfo9);
		
		RankInfo rankSorterNew = rankSorter.get(1L);
		System.out.println(rankSorterNew);
		
		RankInfo rankInfo10 = new RankInfo(6L, "f", 60, timestamp+6);
		rankSorter.put(rankInfo10.getPlayerId(), rankInfo10);
		System.out.println(rankSorter.containKey(rankSorterNew));
		System.out.println(rankSorterNew);
	}
	
	@Test
	public void headSet(){
		long timestamp = System.currentTimeMillis();
		RankSorter rankSorter = new RankSorter(new rankInfoComparator());;
		rankSorter.setMaximum(9);
		
		RankInfo rankInfo1  = new RankInfo(1L, "a", 10, timestamp+1); //+1
		RankInfo rankInfo2  = new RankInfo(2L, "b", 20, timestamp+2); //+2
		RankInfo rankInfo3  = new RankInfo(3L, "c", 30, timestamp+3); //+3
		RankInfo rankInfo4  = new RankInfo(4L, "d", 40, timestamp+4); //+4
		RankInfo rankInfo5  = new RankInfo(5L, "e", 50, timestamp+5); //+5
		RankInfo rankInfo6  = new RankInfo(6L, "f", 60, timestamp+6); //+6
		RankInfo rankInfo7  = new RankInfo(7L, "g", 70, timestamp+7); //+7
		RankInfo rankInfo8  = new RankInfo(8L, "h", 80, timestamp+8); //+8
		RankInfo rankInfo9  = new RankInfo(9L, "e", 90, timestamp+9); //+9
		
		rankSorter.put(rankInfo1.getPlayerId(), rankInfo1);
		rankSorter.put(rankInfo2.getPlayerId(), rankInfo2);
		rankSorter.put(rankInfo3.getPlayerId(), rankInfo3);
		rankSorter.put(rankInfo4.getPlayerId(), rankInfo4);
		rankSorter.put(rankInfo5.getPlayerId(), rankInfo5);
		rankSorter.put(rankInfo6.getPlayerId(), rankInfo6);
		rankSorter.put(rankInfo7.getPlayerId(), rankInfo7);
		rankSorter.put(rankInfo8.getPlayerId(), rankInfo8);
		rankSorter.put(rankInfo9.getPlayerId(), rankInfo9);
		
		for (RankInfo info : rankSorter.values()) {
			System.out.println(info.getPlayerId()+"现在的排名: "+rankSorter.getRank(info));
		}
		Collection<RankInfo> result = null;
		RankInfo rankInfo10 = new RankInfo(5L, "f", 100, timestamp);
		//如果之前未入榜,入榜后截取小于此排名的所有数据
		RankInfo info = rankSorter.get(rankInfo10.getPlayerId());
		if (info == null) {
			rankSorter.put(rankInfo10.getPlayerId(), rankInfo10);
			result = rankSorter.tailSet(rankInfo10);
			System.out.println("10排名:"+rankSorter.getRank(rankInfo10));
		}
		else{
			//如果之前已入榜,则获取被更改的那部分数据
			int rankBefore = rankSorter.getRank(info);
			rankSorter.put(rankInfo10.getPlayerId(), rankInfo10);
			int rankAfter = rankSorter.getRank(rankInfo10);
			result = rankSorter.subRankInfo(rankAfter, rankBefore);
		}
		System.out.println("==============================");
		for (RankInfo rankInfo : result) {
			System.out.println(rankInfo.getPlayerId()+"现在的排名: "+rankSorter.getRank(rankInfo));	
		}
	}
	
	@Test
	public void tailSet(){
		long timestamp = System.currentTimeMillis();
		RankSorter rankSorter = new RankSorter(new rankInfoComparator());;
//		rankSorter.setMaximum(0);
		
		RankInfo rankInfo1  = new RankInfo(1L, "a", 10, timestamp+1);
		RankInfo rankInfo2  = new RankInfo(2L, "b", 20, timestamp+2);
		RankInfo rankInfo3  = new RankInfo(3L, "c", 40, timestamp+3);
		RankInfo rankInfo4  = new RankInfo(4L, "d", 40, timestamp+4);
		RankInfo rankInfo5  = new RankInfo(5L, "e", 50, timestamp+5);
		RankInfo rankInfo6  = new RankInfo(6L, "f", 60, timestamp+6);
		RankInfo rankInfo7  = new RankInfo(7L, "f", 70, timestamp+7);
		RankInfo rankInfo8  = new RankInfo(8L, "f", 80, timestamp+8);
		RankInfo rankInfo9  = new RankInfo(9L, "f", 90, timestamp+9);
		
		rankSorter.put(rankInfo1.getPlayerId(), rankInfo1);
		rankSorter.put(rankInfo2.getPlayerId(), rankInfo2);
		rankSorter.put(rankInfo3.getPlayerId(), rankInfo3);
		rankSorter.put(rankInfo4.getPlayerId(), rankInfo4);
		rankSorter.put(rankInfo5.getPlayerId(), rankInfo5);
		rankSorter.put(rankInfo6.getPlayerId(), rankInfo6);
		rankSorter.put(rankInfo7.getPlayerId(), rankInfo7);
		rankSorter.put(rankInfo8.getPlayerId(), rankInfo8);
		rankSorter.put(rankInfo9.getPlayerId(), rankInfo9);
		
		//开始业务
//		int ranking1before = rankSorter.getRank(rankInfo1);
//		System.out.println(rankInfo1.getPlayerId()+"之前的排名: "+ranking1before);
		for (RankInfo info : rankSorter.values()) {
//			if (info.getPlayerId() == rankInfo1.getPlayerId()) {
//				continue;
//			}
			System.out.println(info.getPlayerId()+"之前的排名: "+rankSorter.getRank(info));
		}
		System.out.println("============================");
		RankInfo rankInfo1_1 = new RankInfo(10L, "a", 100, timestamp+1);
		rankSorter.put(rankInfo1_1.getPlayerId(), rankInfo1_1);
//		int ranking1now = rankSorter.getRank(rankInfo1_1);
//		System.out.println(rankInfo1.getPlayerId()+"现在的排名: "+ranking1now);
		for (RankInfo info : rankSorter.values()) {
//			if (info.getPlayerId() == rankInfo1.getPlayerId()) {
//				continue;
//			}
			System.out.println(info.getPlayerId()+"现在的排名: "+rankSorter.getRank(info));
		}
		//开始截取
		System.out.println("============================");
		Collection<RankInfo> changed = rankSorter.tailSet(rankInfo1_1);
		System.out.print("有变动的:");
		for (RankInfo rankInfo : changed) {
			System.out.print(rankInfo.getPlayerId()+",");
		}
		System.out.println("\n============================");
		System.out.println(rankSorter.headSet(rankInfo1_1));
	}
	
	@Test
	public void testSortMap(){
		long timestamp = System.currentTimeMillis();
		
		SortedValueMap<Long, RankInfo> rankMap = new SortedValueMap<>(new rankInfoComparator());
		
		RankInfo rankInfo1 = new RankInfo(1L, "a", 10, timestamp+1);
		RankInfo rankInfo2 = new RankInfo(2L, "b", 20, timestamp+2);
		RankInfo rankInfo3 = new RankInfo(3L, "c", 40, timestamp+3);
		RankInfo rankInfo4 = new RankInfo(4L, "d", 40, timestamp+4);
		RankInfo rankInfo5 = new RankInfo(5L, "e", 50, timestamp+5);
		
		RankInfo rankInfo6 = new RankInfo(6L, "f", 60, timestamp+6);
		
		rankMap.put(rankInfo1.getPlayerId(), rankInfo1);
		rankMap.put(rankInfo2.getPlayerId(), rankInfo2);
		rankMap.put(rankInfo3.getPlayerId(), rankInfo3);
		rankMap.put(rankInfo4.getPlayerId(), rankInfo4);
		rankMap.put(rankInfo5.getPlayerId(), rankInfo5);
		
		System.out.println(rankMap.size());
		System.out.println(rankMap.getRank(rankInfo3));
		System.out.println(rankMap.getRank(rankInfo4));
		System.out.println(rankMap.getRank(rankInfo6));
	}
	
	@Test
	public void testRankMap(){
		long timestamp = System.currentTimeMillis();
		//Rank<Long, RankInfo> rankMap = new SortedValueMap<>(new rankInfoComparator());
		RankSorter rankSorter = new RankSorter(new rankInfoComparator());;
//		rankSorter.setMaximum(0);
		
		RankInfo rankInfo1 = new RankInfo(1L, "a", 10, timestamp+1);
		RankInfo rankInfo2 = new RankInfo(2L, "b", 20, timestamp+2);
		RankInfo rankInfo3 = new RankInfo(3L, "c", 40, timestamp+3);
		RankInfo rankInfo4 = new RankInfo(4L, "d", 40, timestamp+4);
		RankInfo rankInfo5 = new RankInfo(5L, "e", 50, timestamp+5);
		RankInfo rankInfo6 = new RankInfo(6L, "f", 60, timestamp+6);
		
		rankSorter.put(rankInfo1.getPlayerId(), rankInfo1);
		rankSorter.put(rankInfo2.getPlayerId(), rankInfo2);
		rankSorter.put(rankInfo3.getPlayerId(), rankInfo3);
		rankSorter.put(rankInfo4.getPlayerId(), rankInfo4);
		rankSorter.put(rankInfo5.getPlayerId(), rankInfo5);
		rankSorter.put(rankInfo6.getPlayerId(), rankInfo6);
		
		System.out.println(rankSorter.size());
		System.out.println(rankSorter);
		System.out.println(rankSorter.getRank(rankInfo6));
		System.out.println(rankSorter.getRank(rankInfo4));
	}

	public static class rankInfoComparator implements Comparator<RankInfo>{
		/**
		 * 降序排序
		 * o1<o2 : 返回正数
		 * o1>o2 : 返回负数
		 * o1=o2 : 返回0
		 */
		@Override
		public int compare(RankInfo o1, RankInfo o2) {
			//正常返回值是升序
			int code = Integer.compare(o1.getValue(), o2.getValue());
			if (code == 0) {
				//排名相同,时间早排名靠前
				return Long.compare(o1.getTimestamp(), o2.getTimestamp());
			}
			//设置成降序
			return -code;
		}
		
	} 
	
	
}
