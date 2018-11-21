package fatiny.myTool.rank;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.google.common.collect.Lists;

import fatiny.myTool.rank.util.IPersistence;
import fatiny.myTool.rank.util.ISorter;
import fatiny.myTool.rank.util.Leaderboard;

/**
 * @author Jeremy Feng
 * 排行榜系统独立出来
 */
public class RankContextBackup {
	
	private final Lock lock = new ReentrantLock();
	
	/**
	 * 当触发排行榜, 排行榜产生变动
	 * @param rank
	 */
	public void onTrigger(Leaderboard<Long, ISorter> leaderboard, ISorter sorter) {
		try {
			if (lock.tryLock(1, TimeUnit.SECONDS)) {
				processRank(leaderboard, sorter);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
			
	}
	
	/**
	 * 获取排行榜列表消息, 
	 * @param type
	 * @return 返回两个对象,list(0) = List<OperationMsg.RankMember> or 空集合
	 * 			list(1) = OperationMsg.RankMember or null
	 */
	public Collection<ISorter> getRankList(Leaderboard<Long, ISorter> leaderboard){
//		return sorterActor.sync(new Func<Collection<ISorter>>(){
//			@Override
//			public Collection<ISorter> apply(Object... values) {
				@SuppressWarnings("unchecked")
//				Leaderboard<Long, ISorter> leaderboard = (Leaderboard<Long, ISorter>) values[0];
				//排行榜信息
				Collection<ISorter> sorters = leaderboard.subRankInfo(1, 50);
				return sorters;
//			}
//		},leaderboard);
	}
	
	/**
	 * 获取排行榜列表消息, 
	 * @param type
	 * @return 返回两个对象,list(0) = List<OperationMsg.RankMember> or 空集合
	 * 			list(1) = OperationMsg.RankMember or null
	 */
	public List<Object> getRankList(Leaderboard<Long, ISorter> leaderboard, long objId){
//		return sorterActor.sync(new Func<List<Object>>(){
//			@Override
//			public List<Object> apply(Object... values) {
//				@SuppressWarnings("unchecked")
//				Leaderboard<Long, ISorter> leaderboard = (Leaderboard<Long, ISorter>) values[0];
//				long objId = (Long) values[1];
				//排行榜信息
				Collection<ISorter> sorters = leaderboard.subRankInfo(1, 50);
				//返回值信息
				ISorter sorter = null;
				for (ISorter iSorter : sorters) {
					//如果'我'在榜内
					if (((Long)iSorter.getId()).compareTo(objId) == 0) {
						sorter = iSorter;
					}
				}
				List<Object> result = Lists.newArrayList();
				result.add(sorters);
				result.add(sorter);
				return result;
//			}
//		},leaderboard, objId);
	}
	
	/**
	 * 处理排名, 优化算法, 每次只处理一个玩家入榜
	 * 数据库没有排名的概念, 数据出库在缓存进行排序.所以当数据有变动的时候,直接增加入榜数据和删掉出榜数据即可.
	 * 比如: 100名玩家排行,玩家A未入排行榜. 入榜后remove掉出榜玩家即可
	 */
	private void processRank(Leaderboard<Long, ISorter> leaderboard, ISorter sorter){
		
		if (leaderboard.containsKey(sorter.getId())) {
			//如果玩家之前在榜,则删掉就数据重新添加
			IPersistence p = (IPersistence)sorter;
			p.delete();
		}
		//获取到最后一名玩家
		ISorter lastOne = leaderboard.getLast();
		if (leaderboard.putIfAbove((Long)sorter.getId(), sorter)) {
			//如果玩家入榜,则更新到数据库
			IPersistence p = (IPersistence)sorter;
			p.insert();
		}
		
		if (lastOne != null) {
			if (!leaderboard.containsKey(lastOne.getId())) {
				//如果最后一名被踢掉了,则从库中删除
				IPersistence p = (IPersistence)lastOne;
				p.delete();
			}
		}
	}

}
