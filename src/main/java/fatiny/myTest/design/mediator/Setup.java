package fatiny.myTest.design.mediator;

public abstract class Setup {
	
	private Operation operation;

	public Setup(Operation operation) {
		this.operation = operation;
	}
	
	public Operation getOperation(){
		return operation;
	}
	
}
