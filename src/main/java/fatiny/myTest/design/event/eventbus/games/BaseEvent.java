package fatiny.myTest.design.event.eventbus.games;

public class BaseEvent {
	
	private Player player;

	public BaseEvent(Player player) {
		super();
		this.player = player;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
}
