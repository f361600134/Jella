package fatiny.myTest.design.template.demo1;

public abstract class AbstractTemplate {
	
	public final void prepareGotoSchool(){
		dressUp();
		eatBreakfast();
		takeThings();
	}

	public abstract void dressUp();
	public abstract void eatBreakfast();
	public abstract void takeThings();
	
}
