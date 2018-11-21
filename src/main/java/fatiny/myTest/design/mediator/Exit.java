package fatiny.myTest.design.mediator;

public class Exit extends Setup{
	
	public Exit(Operation operation) {
		super(operation);
	}

	public void doSomething(){
		System.out.println("慢走不送~");
		getOperation().goon(this);
	}

}
