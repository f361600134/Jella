package fatiny.myTest.design.state.match;

/**
 * 以下状态, 全部由时间进行控制.
 * @auth Jeremy
 * @date 2019年3月31日下午11:48:22
 */
public interface IState {
	
	public int Close = 0; //关闭状态
	public int Preliminary = 1; //预赛状态
	public int Prepar = 2;//准备决赛状态
	public int Final = 3;//决赛状态
	public int Finish = 4;//决赛完毕, 发放奖励状态
	
	public void close();//关闭
	public void entryPreliminary();//进入预赛
	public void preparFinal();//准备决赛
	public void entryFinal();//决赛
	public void reward();//发放奖励
	
	
}
