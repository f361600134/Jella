package fatiny.myTest.design.event.eventbus.demo2;

import org.junit.Test;

import com.google.common.eventbus.EventBus;

/**
 * 尝试使用EventBus
 * 
 * @author fsc
 */
public class EventTest {
	
	@Test
	public void test() {
		EventBus bus = new EventBus();
		bus.register(new UserInfoChangeObserver());
		bus.post(new UserInfoChangeEvent("apple"));
	}

}
