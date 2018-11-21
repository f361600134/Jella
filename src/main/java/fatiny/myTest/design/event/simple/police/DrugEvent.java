package fatiny.myTest.design.event.simple.police;

public class DrugEvent extends EventMsg{

	public DrugEvent(String name) {
		super(name);
	}
	
	public DrugEvent(String name, String message) {
		super(name, message);
	}
	
}
