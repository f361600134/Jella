package fatiny.myTool.rank.test;

import java.util.Map;

import com.google.common.collect.Maps;

import fatiny.myTool.rank.util.ISorter;
import fatiny.myTool.rank.util.Leaderboard;
import fatiny.myTool.rank.util.RankComparator;

/**
 * @author Jeremy Feng
 *
 */
public class RankInfoManager {
	
	private RankInfoContext rankCtx;
	private static RankInfoManager manager = new RankInfoManager();
	
	public RankInfoContext getRankCtx() {
		return rankCtx;
	}

	public static RankInfoManager instance(){
		return manager;
	}
	
	/**
	 * 从数据库预加载数据
	 */
	public void init(){
		long timestamp = System.currentTimeMillis();
		Map<Integer, Leaderboard<Long, ISorter>> rankInfoMap = Maps.newHashMap();
		
		RankInfo rankInfo = null;
		String a = "a";
		for (RankType rankType : RankType.values()) {
			Leaderboard<Long, ISorter> leaderboard = 
					new Leaderboard<Long, ISorter>(new RankComparator(), 10);
			
			int type = rankType.getType();
			if (type == RankType.PLAYER_KILLER.getType()) {
				a = a.toLowerCase();
			}else{
				a = a.toUpperCase();
			}
			
			for (int i = 0; i < 10; i++) {
				
				rankInfo = new RankInfo((long)(i+1), a+i, i, timestamp+i); //+1
				leaderboard.put(rankInfo.getId(), rankInfo);
			}
			rankInfoMap.put(type, leaderboard);
		}
		rankCtx = new RankInfoContext(rankInfoMap);
	}
	

}
