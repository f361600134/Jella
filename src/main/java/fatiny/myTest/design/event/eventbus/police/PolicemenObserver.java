package fatiny.myTest.design.event.eventbus.police;

import com.google.common.eventbus.Subscribe;

public class PolicemenObserver extends Police{
	
	
	public PolicemenObserver(){
		super();
	}
	public PolicemenObserver(String name){
		super(name);
	}
	
	@Subscribe
	public void drugEvent(DrugEvent msg) {
		StringBuilder buider = new StringBuilder();
		buider.append("报告总部,我是").append(this.getName()).append(",");
		buider.append(msg.getName()).append("这里有新情况,").append(msg.getMessage());
		System.out.println(buider.toString());
	}
}
