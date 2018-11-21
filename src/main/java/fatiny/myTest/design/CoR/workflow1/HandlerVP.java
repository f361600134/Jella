package fatiny.myTest.design.CoR.workflow1;

public class HandlerVP implements HandlerWork{

	@Override
	public void doHandler(int day) {
		if (day > 3) {
			System.out.println("VP处理3天以上的批假");
		}
	}

}
