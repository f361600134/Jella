package fatiny.myTest.design.facade.demo1;

public class Facade {
	
	private ModuleA moduleA = new ModuleA();
	private ModuleB moduleB = new ModuleB();
	private ModuleC moduleC = new ModuleC();
	
	public void callA(){
		moduleA.doSomething();
	}
	public void callB(){
		moduleB.doSomething();
	}
	public void callC(){
		moduleC.doSomething();
	}
	
}
