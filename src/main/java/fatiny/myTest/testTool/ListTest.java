package fatiny.myTest.testTool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.common.collect.Lists;

public class ListTest {
	
	public static void main(String[] args) {
		try {
			for (int i = 0; i <= 1000000; i++) {
				Thread.sleep(10);
				work();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	static void work(){
		List<Integer> msg = Lists.newArrayList(0, 1, 2);
		List<Integer> data = Lists.newArrayList(11, 22, 33, 44);
		for (int i = 0; i < 100000; i++) {
			msg.add(i);
			data.add(i);
		}
		
		if (data.size() <= msg.size()) {
			System.out.println("参数出错");
			return;
		}
		
		int count = data.size();
		int arr[] = new int[count];
		List<Integer> result = new ArrayList<Integer>();
		for (int index : msg) {
			arr[index] = data.get(index);
		}
		System.out.println(Arrays.toString(arr));
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == 0) {
				arr[i] = 100;
			}
			result.add(arr[i]);
		}
		System.out.println(Arrays.toString(arr));
		System.out.println(result);
	}

}
