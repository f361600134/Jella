package fatiny.myTool.sortMap.older;

/**
 * @author Jeremy Feng
 *
 */
public class RankInfo implements IRanker{
	
	private Long playerId;
	private String name;
	private int value;
	private long timestamp;
	
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
	

}
