package fatiny.myTest.design.event.eventbus.demo1;

import org.junit.Test;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

/**
 * 尝试使用EventBus
 * 
 * @author fsc
 */
public class EventTest {
	
	@Test
	public void test() {
		EventBus bus = new EventBus();
		bus.register(new Object(){
			@Subscribe
			public void handleUserInfoChangeEvent(UserInfoChangeEvent userInfoChangeEvent) {
				System.out.println(userInfoChangeEvent.getEventName()+"改变了");
			}
		});
		bus.post(new UserInfoChangeEvent("apple"));
	}

}
