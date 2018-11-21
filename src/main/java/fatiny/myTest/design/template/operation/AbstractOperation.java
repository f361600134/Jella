package fatiny.myTest.design.template.operation;

import java.util.Random;

/**
 * @author Jeremy Feng
 *
 */
public abstract class AbstractOperation implements IHandler{
	
	private int playerId;
	
	public AbstractOperation(int playerId){
		this.playerId = playerId;
	}
	
	public int getPlayerId(){
		return playerId;
	}
	
	public String getPlayer(){
		return "玩家"+playerId;
	}

	@Override
	public int checkOpen(String active){
		//根据active类型校验是否开启
		Random random = new Random();
		int num = random.nextInt(100);
		if (num <= 33) {
			return IHandler.NONE;
		}
		if (num > 33 && num <= 66) {
			return IHandler.DONE;
		}
		if (num > 66 && num <= 100) {
			return IHandler.REWARDED;
		}
		return IHandler.ERROR;
	}
	
}
