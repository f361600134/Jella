package fatiny.myTest.design.flyweight.chess;

import java.util.Map;

import com.google.common.collect.Maps;

public class ChessFlyWeightFactory {
	
	//享元池
    private static Map<String,ChessFlyWeight> map = Maps.newHashMap();
    
    public ChessFlyWeight getChess(String color){
    	ChessFlyWeight flyWeight = map.get(color);
    	if (flyWeight == null) {
    		flyWeight = new ConcreteFlyWeight(color);
    		map.put(color, flyWeight);
		}
    	return flyWeight;
    }
    
    public static int size(){
    	return map.size();
    }

}
