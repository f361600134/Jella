package fatiny.myTest.thread.fixList;

import fatiny.myTest.apache.map2bean.Stu;
import fatiny.myTest.utils.ConcurrentFixSizeArrayList;

public class ReadReport2 implements Runnable{
	
	private ConcurrentFixSizeArrayList<Stu> list;
	
	public ReadReport2(ConcurrentFixSizeArrayList<Stu> list) {
		this.list = list;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < list.size(); i++) {
			Stu stu = list.get(i);
			System.out.println(Thread.currentThread().getName()+"==>"+stu);
		}
	}

}
