package fatiny.myTest.design.event.simple.police;

/**
 * 抢劫事件
 * @author Administrator
 */
public class RobberyEvent extends EventMsg{

	public RobberyEvent(String name) {
		super(name);
	}
	
	public RobberyEvent(String name, String message) {
		super(name, message);
	}

}
