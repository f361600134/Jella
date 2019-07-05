package fatiny.myTest.thread.fixList;

import java.util.ArrayList;
import java.util.List;

public class WriteReport implements Runnable{
	
	private int start;
	private int end;
	private String name;
	private List<String> list;
	
	public WriteReport(List<String> list, int start, int end, String name) {
		this.list = list;
		this.start = start;
		this.end = end;
		this.name = name;
	}
	
	@Override
	public void run() {
		try {
			List<String> temp = new ArrayList<>();
			for (int i = start; i < end; i++) {
				temp.add(name+i);
			}
			for (String str : temp) {
//				Thread.sleep(1);
				list.add(str);
//				System.out.println(Thread.currentThread().getName()+"==>"+str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
