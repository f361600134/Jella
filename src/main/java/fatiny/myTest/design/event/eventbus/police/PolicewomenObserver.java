package fatiny.myTest.design.event.eventbus.police;

import com.google.common.eventbus.Subscribe;

public class PolicewomenObserver extends Police{
	
	public PolicewomenObserver(){
		super();
	}
	public PolicewomenObserver(String name){
		super(name);
	}
	
	@Subscribe
	public void robberyEvent(RobberyEvent msg) {
		StringBuilder buider = new StringBuilder();
		buider.append("报告总部,我是").append(this.getName()).append(",");
		buider.append(msg.getName()).append("这里有新情况,").append(msg.getMessage());
		System.out.println(buider.toString());
	}

	
}
