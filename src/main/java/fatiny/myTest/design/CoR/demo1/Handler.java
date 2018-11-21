package fatiny.myTest.design.CoR.demo1;

abstract class Handler {
	
	private Handler nextHandler;
	
	public Handler getNextHandler(){
		return nextHandler;
	}
	
	public void setNextHandler(Handler nextHandler){
		this.nextHandler = nextHandler;
	}
	
	public abstract void doHandler();
	
}

class ConcreteHandler extends Handler{
	
	private String name;
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	public ConcreteHandler() {}
	public ConcreteHandler(String name) {
		this.name = name;
	}
	
	@Override
	public void doHandler() {
		if (getNextHandler() != null) {
			System.out.println("还有职责链...继续往下处理");
			getNextHandler().doHandler();
		}else{
			System.out.println("没有职责链了,停止处理...");
		}
	}
	
}

