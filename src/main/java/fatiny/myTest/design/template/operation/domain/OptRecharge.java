package fatiny.myTest.design.template.operation.domain;

import fatiny.myTest.design.template.operation.AbstractOperation;
import fatiny.myTest.design.template.operation.constant.OptData;

/**
 * @author Jeremy Feng
 * 运营活动之连续充值
 */
public class OptRecharge extends AbstractOperation{
	
	private int rechargeNum = 0;

	public OptRecharge(int playerId) {
		super(playerId);
	}

	@Override
	public void refresh() {
		System.out.println(getPlayer()+"下发数据'连续充值'数据");
	}

	@Override
	public void reward(int ...param) {
		int id = param[0];
		int id2 = param[1];
		System.out.println(getPlayer()+"领取:"+id+"和"+id2);
	}
	
	@Override
	public void onTrigger(int... param) {
		int type = param[0];
		int number = param[1];
		if (type == OptData.MATERIAL_GOLD) {
			rechargeNum += number;
			System.out.println(getPlayer()+"运营活动触发充值,当前充值次数:"+rechargeNum);
		}
	}

	@Override
	public void reset() {
		System.out.println("重置连续充值数据");
	}
	
}
