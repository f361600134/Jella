package fatiny.myTest.design.CoR.workflow1;

public class HandlerManager implements HandlerWork{

	@Override
	public void doHandler(int day) {
		if (day <= 1) {
			System.out.println("请假天数小于等于1天, 主管处理");
		}
	}
}
