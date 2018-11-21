package fatiny.myTool.rank.test.event;

import fatiny.myTool.rank.test.Player;

/**
 * @author Jeremy Feng
 *
 */
public class RankChangeEvent {
	
	private Long playerId;
	private String name;
	private int value;
	
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
	
	public RankChangeEvent() {}
	public RankChangeEvent(Long playerId, String name, int value) {
		super();
		this.playerId = playerId;
		this.name = name;
		this.value = value;
	}
	
	public RankChangeEvent(Player player) {
		super();
		this.playerId = player.getPlayerId();
		this.name = player.getName();
		this.value = player.getValue();
	}
	
	

}
