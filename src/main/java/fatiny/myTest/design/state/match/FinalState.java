package fatiny.myTest.design.state.match;

public class FinalState extends AbstractState{
	
	public FinalState(int code, Match match) {
		super(code, match);
	}

	@Override
	public void close() {
		System.out.println("正在决赛中, 不能中断关闭");
	}

	@Override
	public void entryPreliminary() {
		System.out.println("正在决赛中, 不能进入预赛");
	}

	@Override
	public void preparFinal() {
		System.out.println("正在决赛中, 不能再次准备决赛");
	}

	@Override
	public void entryFinal() {
		System.out.println("正在决赛中, 不能再次进入决赛");
	}

	@Override
	public void reward() {
		int day = getMatch().getDay();
		int hour = getMatch().getHour();
		if (day >= 7 && hour >= 22) {
			System.out.println("比赛结束, 发放奖励成功, 准备预赛...");
			getMatch().setCurState(getMatch().getCloseState());
		}else {
			System.out.println("比赛未结束, 不能发放奖励... ");
		}
	}

}
