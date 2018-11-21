package fatiny.myTest.design.template.demo1;

public class Son extends AbstractTemplate{

	@Override
	public void dressUp() {
		System.out.println("穿校服");
	}

	@Override
	public void eatBreakfast() {
		System.out.println("吃妈妈做的早餐");
	}

	@Override
	public void takeThings() {
		System.out.println("背书包, 带上红领巾去上学");
	}

}
