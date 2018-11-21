package fatiny.myTool.rank;

import java.util.Collection;
import java.util.List;

import fatiny.myTool.rank.util.ISorter;
import fatiny.myTool.rank.util.Leaderboard;

/**
 * @author Jeremy Feng
 *
 */
public class RankHelper {
	
	/**
	 * 触发排行榜
	 * @param leaderboard
	 * @param sorter
	 */
	public static void onTrigger(Leaderboard<Long, ISorter> leaderboard, ISorter sorter){
		RankContext rankCtx = RankManager.instance().getRankCtx();
		rankCtx.onTrigger(leaderboard, sorter);
	}
	
	/**
	 * 获取到排行榜显示数据
	 * @param leaderboard
	 * @param objId
	 * @return
	 */
	public static Collection<ISorter> getView(Leaderboard<Long, ISorter> leaderboard){
		RankContext rankCtx = RankManager.instance().getRankCtx();
		return rankCtx.getRankList(leaderboard);
	}
	
	/**
	 * 获取到排行榜显示数据
	 * @param leaderboard
	 * @param objId
	 * @return
	 */
	public static List<Object> getViewPlus(Leaderboard<Long, ISorter> leaderboard, long objId){
		RankContext rankCtx = RankManager.instance().getRankCtx();
		return rankCtx.getRankList(leaderboard, objId);
	}
	
}
