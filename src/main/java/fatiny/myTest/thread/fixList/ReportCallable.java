package fatiny.myTest.thread.fixList;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class ReportCallable implements Callable<Integer>{
	
	private int start;
	private int end;
	private String name;
	private List<String> list;
	
	public ReportCallable(List<String> list, int start, int end, String name){
		this.list = list;
		this.start = start;
		this.end = end;
		this.name = name;
	}

	@Override
	public Integer call() throws Exception {
		try {
			List<String> temp = new ArrayList<>();
			for (int i = start; i < end; i++) {
				temp.add(name+i);
			}
			for (String str : temp) {
				Thread.sleep(1);
				list.add(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}
