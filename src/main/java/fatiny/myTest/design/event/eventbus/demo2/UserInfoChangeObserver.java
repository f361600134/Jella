package fatiny.myTest.design.event.eventbus.demo2;

import com.google.common.eventbus.Subscribe;

public class UserInfoChangeObserver {
	
	@Subscribe
	public void handleUserInfoChangeEvent(UserInfoChangeEvent userInfoChangeEvent) {
		System.out.println(userInfoChangeEvent.getEventName()+"改变了");
	}
	
	
	@Subscribe
	public void handleUserInfoChangeEvent2(UserInfoChangeEvent userInfoChangeEvent) {
		System.out.println(userInfoChangeEvent.getEventName()+"改变了2");
	}
}
