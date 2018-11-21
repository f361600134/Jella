package fatiny.myTool.rank.test;

import fatiny.myTool.rank.util.IPersistence;
import fatiny.myTool.rank.util.ISorter;

/**
 * @author Jeremy Feng
 *
 */
public class RankInfo implements ISorter, IPersistence{
	
	private Long playerId;
	private String name;
	private int value;
	private long timestamp;

	private int ranking;
	
	public int getSetRanking() {
		return ranking;
	}
	public void setRanking(int ranking) {
		this.ranking = ranking;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public Long getPlayerId() {
		return playerId;
	}
	public void setPlayerId(Long playerId) {
		this.playerId = playerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	
	public RankInfo(Long playerId, String name, int value, long timestamp) {
		this.playerId = playerId;
		this.name = name;
		this.value = value;
		this.timestamp = timestamp;
	}
	
	public void setRankInfo(Player player){
		this.playerId = player.getPlayerId();
		this.name = player.getName();
		//this.value = player.getValue();
	}
	
	public RankInfo() {
		super();
	}
	@Override
	public String toString() {
		return "RankInfo [playerId=" + playerId + ", name=" + name + ", value=" + value + "]";
	}
	
	
	@Override
	public Long getId() {
		return playerId;
	}

	@Override
	public Object getFirstOrder() {
		return value;
	}

	@Override
	public Object getSecondOrder() {
		return timestamp;
	}

	@Override
	public void insert() {
//		System.out.println("排行榜数据<插入>");
	}
	
	@Override
	public void update() {
//		System.out.println("排行榜数据<修改>");
	}

	@Override
	public void delete() {
//		System.out.println("排行榜数据<删除>");
	}
	

}
