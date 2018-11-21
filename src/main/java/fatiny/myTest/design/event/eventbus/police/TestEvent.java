package fatiny.myTest.design.event.eventbus.police;

import org.junit.Test;

import com.google.common.eventbus.EventBus;

public class TestEvent {
	
	@Test
	public void test(){
		
		EventBus bus = new EventBus();
		
		bus.register(new PolicemenObserver("警察A"));
		bus.register(new PolicewomenObserver("警察B"));
		bus.register(new PolicewomenObserver("警察C"));
		
		EventMsg msg1 = new DrugEvent("bear", "出了一批新货");
		EventMsg msg2 = new RobberyEvent("tiger", "又抢劫了");
		
		bus.post(msg1);
		bus.post(msg2);
	}

}
