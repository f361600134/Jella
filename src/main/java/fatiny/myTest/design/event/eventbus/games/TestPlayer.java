package fatiny.myTest.design.event.eventbus.games;

import java.util.List;

import com.google.common.collect.Lists;

public class TestPlayer {
	
	public static void main(String[] args) {
		
		//实例化玩家数据
		Player playerA = new Player(1, "A", 100, 100);
		Player playerB = new Player(2, "B", 100, 100);
		List<Player> players = Lists.newArrayList(playerA, playerB);
		
		playerA.setOther(playerB);
		playerB.setOther(playerA);
		
		//注册修改玩家数据事件
		GameEventBus.instance().register(new PlayerChangeObserver());
		
		//开启线程
		List<Thread> result = Lists.newArrayList();
		Thread thread = null;
		for (Player player : players) {
			thread = new Thread(player, player.getName());
			thread.start();
			result.add(thread);
		}
		
		for (Thread t : result) {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//最后打印结果
		System.out.println("end\n"+playerA+"\n"+playerB);
	}

}
