package fatiny.myTest.design.event.simple.police;

/**
 * 嫌疑人接口
 * @author Jeremy
 */
public interface Suspect {
	
	void addObserver(Observer observer);
	
	void removeObserver(Observer observer);
	
	void notice(EventMsg message);
}
