package fatiny.myTest.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Process {
	private static ExecutorService threadPool = Executors.newFixedThreadPool(8);
	
	
	public static void main(String[] args) {
		int result = a();
		System.out.println("a:"+result);
	}
	
	public static int a(){
		int a = 0, b = 1, result = 0;
		try {
			result = b/a;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			result = -1;
		}
		return result;
	}
}
