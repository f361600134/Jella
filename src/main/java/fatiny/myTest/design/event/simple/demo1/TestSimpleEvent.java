package fatiny.myTest.design.event.simple.demo1;

import org.junit.Test;

public class TestSimpleEvent {
	
	/*
	 * 实现,3个警察,监控2个罪犯.
	 * 1警1罪犯, 2警以罪犯.
	 */
	@Test
	public void test(){
		
		//实例化3个警察
		Policemen menA = new Policemen("警察A");
		Policemen menB = new Policemen("警察B");
		Policemen menC = new Policemen("警察C");
		
		//实例化2个罪犯,黑虎和黑熊
		Criminal tiger = new Criminal("tiger");
		Criminal bear = new Criminal("bear");
		
		//添加观察者
		tiger.addObserver(menA);
		bear.addObserver(menB);
		bear.addObserver(menC);
		
		//发送事件,这里注册成事件
		tiger.notice("出了一批新货");
		bear.notice("又抢劫了");
	}

}
