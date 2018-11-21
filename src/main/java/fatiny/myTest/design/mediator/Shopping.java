package fatiny.myTest.design.mediator;

/**
 * 充当调停器
 * @author Administrator
 */
public class Shopping implements Operation{
	
	private Welcome welcome;
	private Exit exit;
	private Pay pay;
	
	
	public Shopping() {
		super();
		this.welcome = new Welcome(this);
		this.exit = new Exit(this);
		this.pay = new Pay(this);
	}

	@Override
	public void goon(Setup setup) {
		if (setup instanceof Welcome) {
			pay.doSomething();
		}
		else if (setup instanceof Pay) {
			exit.doSomething();	
		}
		else {
			System.out.println("买到我喜欢吃的东西啦~");
		}
	}
	
	public void doAction(){
		this.welcome.doSomething();
	}

}
