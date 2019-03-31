package fatiny.myTest.design.state.match;


public class CloseState extends AbstractState{
	
	public CloseState(int code, Match match) {
		super(code, match);
	}

	
	/**
	 * 比赛未开启
	 */
	@Override
	public void close() {
		System.out.println("比赛未开启, 不能关闭");
	}

	/**
	 *未开启状态, 就只能开启预赛
	 */
	@Override
	public void entryPreliminary() {
		int day = getMatch().getDay();
		int hour = getMatch().getHour();
		if (day >= 1 && hour >= 10) {
			System.out.println("准备预赛成功, 进入预赛!!!");
			getMatch().setCurState(getMatch().getPreliminaryState());
		}else {
			System.out.println("预赛未准备好, 不能开始");
		}
	}

	@Override
	public void preparFinal() {
		System.out.println("比赛未开启, 不能准备决赛");
	}

	@Override
	public void entryFinal() {
		System.out.println("比赛未开启, 不能进入决赛");
		
	}

	@Override
	public void reward() {
		System.out.println("比赛未开启, 不能发奖励");
	}

}
