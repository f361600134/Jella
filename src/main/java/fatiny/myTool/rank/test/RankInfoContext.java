package fatiny.myTool.rank.test;

import java.util.Collection;
import java.util.Map;

import fatiny.myTool.rank.Rank;
import fatiny.myTool.rank.util.ISorter;
import fatiny.myTool.rank.util.Leaderboard;
/**
 * @author Jeremy Feng
 *
 */
public class RankInfoContext extends Rank{
	
	public RankInfoContext(){}
	
	public RankInfoContext(Map<Integer, Leaderboard<Long, ISorter>> map){
		super(map);
	}
	
	/**
	 * 刷新单个活动
	 * @param playerCtx
	 * @param aType
	 * @param objId
	 */
	public void refresh(int rankType) {
		Collection<ISorter> sorters = getView(rankType);
		if (sorters == null)return;
		
//		List<OperationRankMsg.Member> members = new ArrayList<>(sorters.size());
//		OperationRankMsg.Member member = null;
		int ranking = 0;
		for (ISorter iSorter : sorters) {
			ranking ++;
			RankInfo ranker = (RankInfo)iSorter;
			ranker.setRanking(ranking);
//			members.add(ranker.toProto());
			System.out.println(ranker);
			//如果'我'在榜内
//			if (((Long)iSorter.getId()).compareTo(playerCtx.getId()) == 0) {
//				member = ranker.toProto();
//			}
		}
		
		System.out.println("==============猥琐的分割线=============");
//		int myrank = -1, myValue = 0;
//		if (member == null) {
//			myrank = -1;
//			myValue = getValue(playerCtx, aType);
//		}else{
//			myrank = member.getRanking();
//			myValue = member.getNumber();
//		}
		
//		OperationRankMsg.S2CRankPlusInfo.Builder builder = OperationRankMsg.S2CRankPlusInfo.newBuilder();
//		builder.setRedPoint(!isRed(playerCtx));
//		builder.setType(rankType);
//		builder.setMyRank(myrank);
//		builder.setMyValue(myValue);
//		builder.addAllMember(members);
//		builder.setStartTime(ActivityBase.getOpenTime(aType));
//		builder.setStopTime(ActivityBase.getStopTime(aType));
//		builder.setState(getState(aType));
//		playerCtx.push(SimpleProtocol.create(ProtocolCode.OPERATION_S2C_RANK_PLUS, builder.build()));
	}
	
	/**
	 * 刷新单个活动
	 * @param playerCtx
	 * @param aType
	 * @param objId
	 */
	public void refresh() {
		for (RankType rankType : RankType.values()) {
			Collection<ISorter> sorters = getView(rankType.getType());
			if (sorters == null)return;
			int ranking = 0;
			for (ISorter iSorter : sorters) {
				ranking ++;
				RankInfo ranker = (RankInfo)iSorter;
				ranker.setRanking(ranking);
				System.out.println(ranker);
			}
		}
		System.out.println("==============猥琐的分割线=============");
	}
	
	
	
	/**
	 * 根据id发放奖励奖励
	 * @param rankType = param[0]
	 */
	public void reward(int rankType) {
		Leaderboard<Long, ISorter> leaderboard = sorterMap.get(rankType);
		Collection<ISorter> sorters = leaderboard.values();
//		List<Material> reward = Lists.newArrayList();
		for (ISorter sorter : sorters) {
			try {
				int ranking = leaderboard.getRank(sorter);
				System.out.println("您获得第["+ranking+"]名, 获取牛逼的东西");
				//记录排行榜榜单
//				IntegralBase base = IntegralBase.get(rankType, ranking);
//				if (base == null) {
//					GameLog.error("获取邮件奖励配置数据出错, 策划配错表了, rankType={}, ranking={}", rankType, ranking);
//					continue;
//				}
//				reward = Material.analyze(base.getAwordID());
//				String title = getTitle(rankType);
//				MailBase mailBase = MailBase.get(Integer.valueOf(title));
//				PlayerMail.sendMail((Long)sorter.getId(), MailType.SYSTEM, title, mailBase.getText() + ";" + ranking, reward);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
