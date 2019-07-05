package fatiny.myTest.thread.fixList;

import java.util.List;

public class ReadReport implements Runnable{
	
	private List<String> list;
	
	public ReadReport(List<String> list) {
		this.list = list;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < list.size(); i++) {
			String str = list.get(i);
			System.out.println(Thread.currentThread().getName()+"==>"+str);
		}
	}

}
