package fatiny.myTest.design.event.eventbus.demo2;

public class BaseEvent {
	
	private String eventName;

	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	
	public BaseEvent(String eventName) {
		super();
		this.eventName = eventName;
	}
	
	

}
