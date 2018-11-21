package fatiny.myTest.design.template.operation.domain;

import fatiny.myTest.design.template.operation.AbstractOperation;

/**
 * @author Jeremy Feng
 *
 */
public class OptRobotman extends AbstractOperation{
	
	private int optRobotTime = 0;

	public OptRobotman(int playerId) {
		super(playerId);
	}

	@Override
	public void refresh() {
		System.out.println(getPlayerId()+"下发'机甲大师'数据");
	}

	@Override
	public void reward(int... param) {
		int id = param[0];
		System.out.println(getPlayerId()+"机甲大师领取奖励:"+id);
	}

	@Override
	public void onTrigger(int... param) {
		optRobotTime ++;
		System.out.println(getPlayerId()+"使用机甲次数:"+optRobotTime);
	}

	@Override
	public void reset() {
		System.out.println("重置机甲大师数据");
	}

}
