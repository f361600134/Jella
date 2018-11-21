package fatiny.myTest.design.event.simple.police;

public class EventMsg {
	
	private String name;
	private String Message;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMessage() {
		return Message;
	}
	public void setMessage(String message) {
		Message = message;
	}
	
	public EventMsg(String name, String message) {
		super();
		this.name = name;
		Message = message;
	}
	
	public EventMsg(String message) {
		super();
		Message = message;
	}
}
