package fatiny.myTest.design.state.match;

public class Test {
	
	public static void main(String[] args) {
		Match match = new Match();
		match.setCurState(match.getCloseState());
		match.entryFinal();
		//未开启状态进入预赛
		match.setDay(1);
		match.setHour(10);
		match.entryPreliminary();
		//预赛中, 准备决赛
		match.setDay(6);
		match.setHour(22);
		match.preparFinal();
		//准备决赛成功, 进入决赛
		match.setDay(7);
		match.setHour(10);
		match.entryFinal();
		//准备决赛成功, 进入决赛
		match.reward();
		match.setDay(7);
		match.setHour(22);
		match.reward();
		
	}

}
