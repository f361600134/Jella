package fatiny.myTool.rank;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;

import fatiny.myTool.rank.util.IPersistence;
import fatiny.myTool.rank.util.ISorter;
import fatiny.myTool.rank.util.Leaderboard;

/**
 * @author Jeremy Feng
 * 排行榜抽象类
 * 排行榜类型其实要做到唯一
 */
public abstract class Rank{
	
	/**
	 * 排行榜榜单
	 * @Key: 排行榜类型
	 * @value: 排行榜单
	 */
	public Map<Integer, Leaderboard<Long, ISorter>> sorterMap;
	
	public Rank(){}
	public Rank(Map<Integer, Leaderboard<Long, ISorter>> sorterMap){
		this.sorterMap = sorterMap;
	}
	
	/**
	 * 根据id重置所有清除所有排行榜数据
	 */
	public void clear(int type) {
		Leaderboard<Long, ISorter> leaderboard = sorterMap.get(type);
		removeAll(leaderboard);
		sorterMap.clear();
	}

	/**
	 * 清除所有数据.
	 * 如果有多层结构, 会一并清除掉.
	 */
	public void clear() {
		for (Leaderboard<Long, ISorter> leaderboard : sorterMap.values()) {
			removeAll(leaderboard);
		}
		sorterMap.clear();
	}
	
	/**
	 * 重置数据
	 * 结构不会清掉,只会清除子结构里面的数据
	 */
	public void reset() {
		for (Leaderboard<Long, ISorter> leaderboard : sorterMap.values()) {
			removeAll(leaderboard);
			leaderboard.clear();
		}
	}
	
	/**
	 * 根据id重置数据
	 * 结构不会清掉,只会清除子结构里面的数据
	 */
	public void reset(int type) {
		Leaderboard<Long, ISorter> leaderboard = sorterMap.get(type);
		removeAll(leaderboard);
		leaderboard.clear();
	}
	
	/**
	 * 清掉排行榜和数据库数据
	 * @param leaderboard
	 */
	private void removeAll(Leaderboard<Long, ISorter> leaderboard) {
		Collection<ISorter> operationRanks = leaderboard.values();
		for (ISorter iSorter : operationRanks) {
			//清除排行表数据
			IPersistence p = (IPersistence)iSorter;
			p.delete();
		}
	}
	
	/**
	 * 得到第一
	 * @return
	 */
	public ISorter getFirst(int type){
		Leaderboard<Long, ISorter> leaderboard = sorterMap.get(type);
		return leaderboard.getFirst();
	}
	
	/**
	 * 得到第x名
	 * @return
	 */
	public Collection<ISorter> getLimit(int type, int limit){
		Collection<ISorter> result;
		try {
			Leaderboard<Long, ISorter> leaderboard = sorterMap.get(type);
			if (leaderboard != null) {
				result = leaderboard.subRankInfo(1, limit);
			}
		} catch (Exception e) {
			//GameLog.error("", e);
			e.printStackTrace();
		}finally {
			result = Lists.newArrayList();
		}
		return result;
	}
	
	/**
	 * 获取到排行榜信息及自己的排名
	 * @param rankType
	 * @param objId
	 * @return
	 */
	public Collection<ISorter> getView(int rankType) {
		Leaderboard<Long, ISorter> leaderboard = sorterMap.get(rankType);
		if (leaderboard == null) {
			//GameLog.error("getView 排行榜类型错误, rankType:{}", rankType);
			return Lists.newArrayList();
		}
		return RankHelper.getView(leaderboard);
	}
	
	/**
	 * 获取到排行榜信息及自己的排名
	 * @param rankType
	 * @param objId
	 * @return
	 */
	public List<Object> getViewPlus(int rankType, long objId) {
		Leaderboard<Long, ISorter> leaderboard = sorterMap.get(rankType);
		if (leaderboard == null) {
			//GameLog.error("getViewPlus 排行榜类型错误, rankType:{}", rankType);
			return Lists.newArrayList();
		}
		return RankHelper.getViewPlus(leaderboard, objId);
	}
	
	/**
	 * 获取到排行榜信息
	 * @param rankType
	 * @param objId
	 * @return
	 */
	public Leaderboard<Long, ISorter> getRankList(int rankType) {
		Leaderboard<Long, ISorter> leaderboard = sorterMap.get(rankType);
		return leaderboard;
	}
	
	/**
	 * 获取到指定信息的排名
	 * @param rankType
	 * @param objId
	 * @return
	 */
	public int getRanking(int rankType, long objId) {
		Leaderboard<Long, ISorter> leaderboard = sorterMap.get(rankType);
		ISorter sorter = leaderboard.get(objId);
		if (sorter != null) {
			return leaderboard.getRank(sorter);
		}
		return 0;
	}
	
	/**
	 * 当触发排行榜, 排行榜产生变动
	 * @param LegionWarfareRank
	 */
	public void onTrigger(int rankType, ISorter sorter){
		Leaderboard<Long, ISorter> leaderboard = sorterMap.get(rankType);
		// 通过排行榜模块进行排行
		RankHelper.onTrigger(leaderboard, sorter);
	}
	
}
