package fatiny.myTool.sortMap.older;

import java.util.Collection;
import java.util.Comparator;

import fatiny.myTool.sortMap.common.SortedValueMap;

/**
 * 活动排行榜专用
 * @author Jeremy Feng
 */
public class RankSorter {
	
	private static int DEFAULT_CAPACITY = -1;
	private SortedValueMap<Long, RankInfo> map;
	/*
     * 可排最大量,-1表示不限制
     * 超过最大量的数据会被移除掉
     */
	private int maximum;
	
	public RankSorter(Comparator<RankInfo> comparator){
		maximum = -1;
		map = new SortedValueMap<>(comparator);
	}
	
	public RankSorter(Comparator<RankInfo> comparator, int maximum){
		this.maximum = maximum;
		map = new SortedValueMap<>(comparator);
	}

	/**
	 * 设置排行榜最大量
	 * @param maximum
	 */
	public void setMaximum(int maximum) {
		if (maximum == 0) {
			throw new IllegalArgumentException("the input parameter {maximum} is not allowed be zero");
		}
		this.maximum = maximum;
	}

	/**
	 * 溢出则溢出
	 */
	public void removeIfoverflow(){
		if (maximum > 1 && map.size() > maximum) {
			int count = map.size() - maximum;
			for (int i = 0; i < count; i++) {
				RankInfo rankInfo = map.getLast();
				map.remove(rankInfo.getPlayerId());
			}
		}
	}
	
	/**
	 * put数据
	 * @param playerId
	 * @param rankInfo
	 */
	public void put(Long playerId, RankInfo rankInfo){
		map.put(playerId, rankInfo);
		removeIfoverflow();
	}
	
	public RankInfo get(Long playerId){
		return map.get(playerId);
	}
	
	/**
	 * 返回排行榜数据
	 */
	public int size(){
		return map.size();
	}
	
	/**
	 * 返回排行榜数据
	 */
	public boolean containKey(Object key){
		return map.containsKey(key);
	}
	
	/**
	 * 返回排名数据
	 */
	public int getRank(RankInfo rankInfo){
		int ranking = map.getRank(rankInfo);
		if (maximum > DEFAULT_CAPACITY && ranking > maximum) {
			ranking = DEFAULT_CAPACITY;
		}
		return ranking;
	}
	
	/**
	 * 通过排名,获取到对象
	 * @param index
	 * @return RankInfo
	 */
	public RankInfo getRankInfo(int index){
		return map.getRank(index);
	}
	
	/**
	 * 返回排行榜数据
	 */
	public Collection<RankInfo> values() {
		return map.values();
	}

	public Collection<RankInfo> tailSet(RankInfo rankInfo){
		return map.tailSet(rankInfo);
	}
	
	public Collection<RankInfo> headSet(RankInfo rankInfo){
		return map.headSet(rankInfo);
	}
	
	public Collection<RankInfo> subSet(RankInfo fromRank, RankInfo toRank){
		return map.subSet(fromRank, toRank);
	}
	
	public Collection<RankInfo> subRankInfo(int fromIndex, int toIndex){
		return map.subRank(fromIndex, toIndex);
	}
	
	
	
	@Override
	public String toString() {
		return "RankSorter [map=" + map + "]";
	}

}
