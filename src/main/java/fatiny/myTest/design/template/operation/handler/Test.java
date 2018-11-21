package fatiny.myTest.design.template.operation.handler;

import com.google.common.eventbus.EventBus;

import fatiny.myTest.design.template.operation.Control;
import fatiny.myTest.design.template.operation.IHandler;
import fatiny.myTest.design.template.operation.constant.OptData;
import fatiny.myTest.design.template.operation.domain.OptCaptain;
import fatiny.myTest.design.template.operation.domain.OptRecharge;
import fatiny.myTest.design.template.operation.domain.OptRobotman;
import fatiny.myTest.design.template.operation.event.OptJob;
import fatiny.myTest.design.template.operation.event.OptObserver;
import fatiny.myTest.design.template.operation.event.UserResourceEvent;

/**
 * @author Jeremy Feng
 *
 */
public class Test {
	
	public static void main(String[] args) {
		//init
		int playerId = 1;
		Control control = Control.instance(playerId);
		
		IHandler optCaptain = control.getHandler(OptCaptain.class);
		check(optCaptain);
		optCaptain.refresh();
		optCaptain.reward(1001);
		
		System.out.println();
		
		IHandler optRecharge = control.getHandler(OptRecharge.class);
		check(optRecharge);
		optRecharge.refresh();
		optRecharge.reward(1001, 1002);
		
		System.out.println();
		
		IHandler optRobotman = control.getHandler(OptRobotman.class);
		check(optRobotman);
		optRobotman.reward(1001);
		optRobotman.refresh();
		
		//计时器
		new OptJob().run();
		
		EventBus bus = new EventBus();
		bus.register(new OptObserver());
		bus.post(new UserResourceEvent(OptData.MATERIAL_GOLD,2));
//		bus.post(new UserResourceEvent(OptData.MATERIAL_HONOR,3));
	}
	
	public static void check(IHandler handler){
		int errorCode = handler.checkOpen(handler.getClass().getSimpleName());
		switch (errorCode) {
			case IHandler.ERROR:System.out.println("错误");break;
			case IHandler.NONE:System.out.println("未完成");break;
			case IHandler.DONE:System.out.println("已完成");break;
			case IHandler.REWARDED:System.out.println("已领取");break;
		}
	}
	
	

}
