package fatiny.myTest.design.mediator;

public class Pay extends Setup{
	
	public Pay(Operation operation) {
		super(operation);
	}

	public void doSomething(){
		System.out.println("您好,您需要支付5元~");
		getOperation().goon(this);
	}

}
