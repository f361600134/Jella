package fatiny.myTest.design.template.operation.domain;

import fatiny.myTest.design.template.operation.AbstractOperation;
import fatiny.myTest.design.template.operation.constant.OptData;

/**
 * @author Jeremy Feng
 *
 */
public class OptCaptain extends AbstractOperation{
	
	private int captainNum = 0;

	public OptCaptain(int playerId) {
		super(playerId);
	}

	@Override
	public void refresh() {
		System.out.println(getPlayer()+"下发'军官福利'数据");
	}

	@Override
	public void reward(int... param) {
		int id = param[0];
		System.out.println(getPlayer()+"领取:"+id);
	}
	
	@Override
	public void onTrigger(int... param) {
		int type = param[0];
		int number = param[1];
		if (type == OptData.MATERIAL_HONOR) {
			captainNum += number;
			System.out.println(getPlayer()+"军官福利触发充值,当前充值次数:"+captainNum);
		}
	}

	@Override
	public void reset() {
		System.out.println("重置军官福利数据");
	}

}
