package fatiny.myTool.timeMap;

import org.junit.Test;

public class TimeMapTest {
	
	@Test
	public void test(){
		
		TimeMap<Integer, IAssist> map = 
				new TimeMap<Integer, IAssist>(1000);
		
		map.put(1, new Assist(1, 1));
		map.put(2, new Assist(2, 2));
		map.put(3, new Assist(3, 3));
		
		System.out.println(map.get(1));
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(map.get(1));
	}
	
	public static class Assist implements IAssist{
		
		private int id;
		private int num;
		
		public Assist(int id, int num){
			this.id = id;
			this.num = num;
		}

		@Override
		public int getAId() {
			return id;
		}

		@Override
		public Number getAValue() {
			return num;
		}

		@Override
		public void addAValue(Number value) {
			this.num = num + (Integer)value;
		}

		@Override
		public String toString() {
			return "Assist [id=" + id + ", num=" + num + "]";
		}
	}

}
