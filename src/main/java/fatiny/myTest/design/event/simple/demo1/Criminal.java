package fatiny.myTest.design.event.simple.demo1;

import java.util.HashSet;
import java.util.Set;

/**
 * 罪犯,实现了嫌疑人接口.
 * @author Administrator
 */
public class Criminal implements Suspect{
	
	private String name;
	private Set<Observer> observerSet;

	public Criminal() {
		this.observerSet = new HashSet<Observer>();
	}
	
	public Criminal(String name) {
		this.name = name;
		this.observerSet = new HashSet<Observer>();
	}
	
	@Override
	public void addObserver(Observer observer) {
		if (!observerSet.contains(observer)) {
			observerSet.add(observer);
		}
	}

	@Override
	public void removeObserver(Observer observer) {
		if (observerSet.contains(observer)) {
			observerSet.remove(observer);
		}
	}

	@Override
	public void notice(String message) {
		for (Observer observer : observerSet) {
			observer.update(message, this.name);
		}
	}

}
