package fatiny.myTest.design.CoR.workflow;

public class HandlerManager extends HandlerWork{

	@Override
	public void doHandler(int day) {
		if (day <= 1) {
			System.out.println("请假天数小于等于1天, 主管处理");
		}
		else{
			getNextHandler().doHandler(day);
		}
	}
}
