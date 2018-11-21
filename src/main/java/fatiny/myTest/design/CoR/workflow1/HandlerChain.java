package fatiny.myTest.design.CoR.workflow1;

import java.util.ArrayList;
import java.util.List;

public class HandlerChain implements HandlerWork{
	
	List<HandlerWork> workList = new ArrayList<>();
	public List<HandlerWork> getWorkList() {
		return workList;
	}
	public void setWorkList(List<HandlerWork> workList) {
		this.workList = workList;
	}
	public void addWork(HandlerWork work) {
		this.workList.add(work);
	}
	
	public HandlerChain appendWork(HandlerWork work) {
		this.workList.add(work);
		return this;
	}
	
	
	
	@Override
	public void doHandler(int day) {
		for (HandlerWork handlerWork : workList) {
			handlerWork.doHandler(day);
		}
	}

}
