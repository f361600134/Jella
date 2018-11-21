package fatiny.myTool.rank.test;

import java.util.List;

import com.google.common.collect.Lists;

import fatiny.myTool.rank.test.common.GameEventBus;
import fatiny.myTool.rank.test.event.RankObserver;

/**
 * @author Jeremy Feng
 *
 */
public class RankTest {
	
	public static void main(String[] args) {
		
		//初始化系统数据
		RankInfoManager.instance().init();
		RankInfoContext rankInfoContext = RankInfoManager.instance().getRankCtx();
		rankInfoContext.refresh();
		
		//注册事件
		GameEventBus.instance().register(new RankObserver());
		
		System.out.println();
		
		//玩家数据
		Player Jeremy = new Player(1001L, "Jeremy", 100);
		Player Lora = new Player(1002L, "Lora", 100);
//		Player Jake = new Player(1003L, "Jake", 100);
//		Player Li = new Player(1004L, "Li", 100);
		
		List<Player> players = Lists.newArrayList(Jeremy, Lora);
//		List<Player> players = Lists.newArrayList(Jeremy, Lora, Jake, Li);
		

//		Thread jeremy = new Thread(Jeremy);
//		jeremy.start();
//		Thread lora = new Thread(Lora);
//		lora.start();
//		Thread jake = new Thread(Jake);
//		jake.start();
//		Thread li= new Thread(Li);
//		li.start();
		
		
//		try {
//			jeremy.join();
//			lora.join();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		
		Thread thread = null;
		for (Player player : players) {
			thread = new Thread(player);  
			thread.start();
//			try {
//				thread.join();
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
		}
		
		//
		rankInfoContext.refresh();
	}

}
















