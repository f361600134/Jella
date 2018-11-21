package fatiny.myTest.design.mediator;

public class Welcome extends Setup{
	
	public Welcome(Operation operation) {
		super(operation);
	}

	public void doSomething(){
		System.out.println("欢迎您~");
		getOperation().goon(this);
	}

}
