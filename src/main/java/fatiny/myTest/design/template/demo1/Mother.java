package fatiny.myTest.design.template.demo1;

public class Mother extends AbstractTemplate{

	@Override
	public void dressUp() {
		System.out.println("起床, 穿围裙");
	}

	@Override
	public void eatBreakfast() {
		System.out.println("和孩子一起吃我做的早餐");
	}

	@Override
	public void takeThings() {
		System.out.println("带上包, 送孩子去上学");
	}

}
