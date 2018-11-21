package fatiny.myTest.design.CoR.workflow;

public class HandlerVP extends HandlerWork{

	@Override
	public void doHandler(int day) {
		if (day > 3) {
			System.out.println("VP处理3天以上的批假");
		}
		else{
			getNextHandler().doHandler(day);
		}
	}

}
