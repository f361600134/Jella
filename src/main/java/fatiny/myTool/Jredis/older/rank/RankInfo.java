package fatiny.myTool.Jredis.older.rank;

/**
 * @author Jeremy Feng
 *
 */
public class RankInfo implements ISorter{
	
	private Long playerId;
	private String name;
	private double value;
	private long timestamp;
	
	//无需保存
	private transient int rank;//名次
	
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
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	
	public RankInfo(Long playerId, String name, double value, long timestamp) {
		this.playerId = playerId;
		this.name = name;
		this.value = value;
		this.timestamp = timestamp;
	}
	
	public RankInfo(Long playerId, double value) {
		this.playerId = playerId;
		this.value = value;
	}
	
	public RankInfo() {
		super();
	}
	
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	@Override
	public String toString() {
		return "RankInfo [playerId=" + playerId + ", value=" + value
				+ ", rank=" + rank + "]";
	}
	@Override
	public String getSortId() {
		return String.valueOf(playerId);
	}
	@Override
	public double getSortValue() {
		return value;
	}
	@Override
	public Object getSecondOrder() {
		return timestamp;
	}

}
