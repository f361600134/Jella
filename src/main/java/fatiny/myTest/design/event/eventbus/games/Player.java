package fatiny.myTest.design.event.eventbus.games;

public class Player implements Runnable{

	private int id;
	private String name;
	private int hp;
	private int gold;
	private volatile int number;
	
	private Player other;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	
	public void addHp(int hp){
		this.hp += hp;
//		if (this.hp <= 0) {
//			this.hp = 0;
//		}
	}
	
	public void addGold(int gold){
		this.gold += gold;
//		if (this.gold <= 0) {
//			this.gold = 0;
//		}
	}
	public Player(int id, String name, int hp, int gold) {
		super();
		this.id = id;
		this.name = name;
		this.hp = hp;
		this.gold = gold;
	}
	
	public void setOther(Player other){
		this.other = other;
	}
	
	@Override
	public String toString() {
		return "Player [id=" + id + ", name=" + name + ", hp=" + hp + ", gold="
				+ gold + "]";
	}
	
	/**
	 * 玩家给对方增加指定的数, 自己减去相应的数
	 */
	@Override
	public void run() {
		int hp = 10;
		int gold = 10;
		
		for (int i = 0; i < 100000; i++) {
			GameEventBus.instance().post(new PlayerChangeEvent(other, hp, gold));
//			GameEventBus.instance().post(new PlayerChangeEvent(this, -hp, -gold));
			addHp(-hp);
			addGold(-gold);
//			try {
//				Thread.sleep(10);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
		}
	}
	
}
