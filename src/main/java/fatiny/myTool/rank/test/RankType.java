package fatiny.myTool.rank.test;

import java.util.Random;

/**
 * @author Jeremy Feng
 * 排行榜模块
 */
public enum RankType {
	 PLAYER_KILLER(1) ,
	 PLAYER_LEGION(2),
	 ;
	
	private int type;
	private RankType(int type){
		this.type = type;
	}
	
	public int getType(){
		return type;
	}
	
	public static int getRundomType(){
//		return new Random().nextInt(2)+1;
		return 1;
	}
	
}








