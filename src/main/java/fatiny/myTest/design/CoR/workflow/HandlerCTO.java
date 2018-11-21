package fatiny.myTest.design.CoR.workflow;

public class HandlerCTO extends HandlerWork{

	@Override
	public void doHandler(int day) {
		if (day <= 3) {
			System.out.println("CTO处理小于等于3的批假");
		}
		else{
			getNextHandler().doHandler(day);
		}
	}

}
