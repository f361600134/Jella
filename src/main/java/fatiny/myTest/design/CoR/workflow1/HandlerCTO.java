package fatiny.myTest.design.CoR.workflow1;

public class HandlerCTO implements HandlerWork{

	@Override
	public void doHandler(int day) {
		if (day <= 3) {
			System.out.println("CTO处理小于等于3的批假");
		}
	}

}
