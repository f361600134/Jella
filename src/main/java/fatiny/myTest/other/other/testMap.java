
package fatiny.myTest.other.other;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

import com.google.common.collect.Maps;

/**
 * @author Jeremy Feng
 *
 */
public class testMap {
	
	public static class Base{
		private ConcurrentMap<Integer,Map<Integer,Integer>> map = Maps.newConcurrentMap();
		
		public Base(){
			Map<Integer,Integer> teMap = Maps.newHashMap();
			teMap.put(1, 1);
			teMap.put(2, 2);
			teMap.put(3, 3);
			map.put(1, teMap);
			
			Map<Integer,Integer> teMap1 = Maps.newHashMap();
			teMap1.put(11, 1);
			teMap1.put(22, 2);
			teMap1.put(33, 3);
			map.put(2, teMap1);
		}
		
		public Map<Integer, Integer> getLotteryNumMap(Integer playerId) {
			Map<Integer, Integer> result = map.get(playerId);
			if(result == null){
				result = new HashMap<>();
				map.put(playerId, result);
			}
			return result;
		}
		
		public void print(){
			System.out.println(map);
		}
		
	}
	
	public static void main(String[] args) {
		int playerId = 1, id = 1;
		Base base = new Base();
		base.getLotteryNumMap(1);
		base.print();
		Map<Integer, Integer> result = base.getLotteryNumMap(playerId);
		base.getLotteryNumMap(1).clear();
		base.print();
		Integer integer = result.get(id);
		if(integer==null)
			integer = 0;
		
		int num = integer.intValue() + 1;
		if(num > 5)
			System.out.println("今日次数已达上限");
		
		result.put(id,num);
		base.print();
	}

}
