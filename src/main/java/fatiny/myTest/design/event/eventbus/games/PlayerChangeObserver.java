package fatiny.myTest.design.event.eventbus.games;

import com.google.common.eventbus.Subscribe;

/**
 * 玩家修改订阅事件
 * @author Jeremy
 */
public class PlayerChangeObserver {

	@Subscribe
	public void playerChangeEvent(PlayerChangeEvent event) {
		Player player = event.getPlayer();
		
		int gold = event.getGold();
		int hp = event.getHp();
		
		player.addGold(gold);
		player.addHp(hp);
		
		//System.out.println(player);
		System.out.println("====> "+player);
		
	}

}
