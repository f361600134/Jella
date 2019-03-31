package fatiny.myTest.design.state.match;

public class PreliminaryState extends AbstractState{

	public PreliminaryState(int code, Match match) {
		super(code, match);
	}

	
	@Override
	public void close() {
		System.out.println("正在预赛中, 不能中断关闭比赛");
	}

	@Override
	public void entryPreliminary() {
		System.out.println("正在预赛中, 不能再次进入预赛");
	}

	@Override
	public void preparFinal() {
		int day = getMatch().getDay();
		int hour = getMatch().getHour();
		if (day >= 6 && hour >= 22) {
			System.out.println("准备决赛成功!!!");
			getMatch().setCurState(getMatch().getPreparState());
		}else {
			System.out.println("未到开启时间, 不能准备决赛");
		}
	}

	@Override
	public void entryFinal() {
		System.out.println("正在预赛中, 不能直接进入决赛");
	}

	@Override
	public void reward() {
		System.out.println("正在预赛中, 不能直接发放奖励");
	}

}
