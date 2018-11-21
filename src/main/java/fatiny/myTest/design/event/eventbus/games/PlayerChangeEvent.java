package fatiny.myTest.design.event.eventbus.games;

/**
 * 使用订阅者对玩家对象进行修改
 * @author Jeremy
 */
public class PlayerChangeEvent extends BaseEvent{
	
	private int hp;
	private int gold;

	public PlayerChangeEvent(Player player, int hp, int gold) {
		super(player);
		this.hp = hp;
		this.gold = gold;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}
	
}
