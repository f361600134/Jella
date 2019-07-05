package fatiny.myTest.thread.fixList;

import java.util.ArrayList;
import java.util.List;

import fatiny.myTest.apache.map2bean.Stu;
import fatiny.myTest.utils.ConcurrentFixSizeArrayList;

public class WriteReport2 implements Runnable{
	
	private int start;
	private int end;
	private String name;
	private ConcurrentFixSizeArrayList<Stu> list;
	
	public WriteReport2(ConcurrentFixSizeArrayList<Stu> list, int start, int end, String name) {
		this.list = list;
		this.start = start;
		this.end = end;
		this.name = name;
	}
	
	@Override
	public void run() {
		try {
			List<Stu> temp = new ArrayList<>();
			for (int i = start; i < end; i++) {
				temp.add(new Stu(i, name+i));
			}
			for (Stu stu : temp) {
				list.add(stu);
				Thread.sleep(10);
				System.out.println(Thread.currentThread().getName()+"==>"+stu.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
