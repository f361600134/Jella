package fatiny.myTest.design.CoR.workflow;

public abstract class HandlerWork {
	
	private HandlerWork nextHandler;
	
	public HandlerWork getNextHandler() {
		return nextHandler;
	}
	public void setNextHandler(HandlerWork nextHandler) {
		this.nextHandler = nextHandler;
	}
	
	public abstract void doHandler(int day);

}
