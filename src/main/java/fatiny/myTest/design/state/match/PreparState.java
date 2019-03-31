package fatiny.myTest.design.state.match;

public class PreparState extends AbstractState{

	public PreparState(int code, Match match) {
		super(code, match);
	}

	@Override
	public void close() {
		System.out.println("正在准备决赛中, 不能直接中断关闭");
	}

	@Override
	public void entryPreliminary() {
		System.out.println("正在准备决赛中, 不能再次进入预赛");
	}

	@Override
	public void preparFinal() {
		System.out.println("正在准备决赛中, 不能再次进入准备决赛");
	}

	@Override
	public void entryFinal() {
		int day = getMatch().getHour();
		int hour = getMatch().getHour();
		if (day >= 7 && hour >= 10) {
			System.out.println("进入决赛成功!!!");
			getMatch().setCurState(getMatch().getFinalState());
		}else {
			System.out.println("未到开启时间, 不能进入决赛...");
		}
	}

	@Override
	public void reward() {
		System.out.println("正在准备决赛中, 不能直接发放奖励");
	}

}
