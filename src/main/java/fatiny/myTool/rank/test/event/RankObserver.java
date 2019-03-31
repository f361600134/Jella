package fatiny.myTool.rank.test.event;

import com.google.common.eventbus.Subscribe;

import fatiny.myTool.rank.test.RankInfo;
import fatiny.myTool.rank.test.RankInfoContext;
import fatiny.myTool.rank.test.RankInfoManager;
import fatiny.myTool.rank.test.RankType;

/**
 * @author Jeremy Feng
 *
 */
public class RankObserver {
	
	@Subscribe
	public void rankChangeEvent(RankChangeEvent event) {
		
		RankInfoContext rankContext = RankInfoManager.instance().getRankCtx();
		RankInfo rankInfo = new RankInfo();
		long time = System.currentTimeMillis();
		
		
		int rankType = RankType.getRundomType();
		rankInfo.setPlayerId(event.getPlayerId());
		rankInfo.setName(event.getName());
		rankInfo.setValue(event.getValue());
		rankInfo.setTimestamp(time);
		rankContext.onTrigger(rankType, rankInfo);
		rankContext.refresh(rankType);
		
	}

}
