package fatiny.myTest.thread.fixList;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import fatiny.myTest.utils.ConcurrentFixSizeArrayList3;

public class Test {
	
//	public static void main(String[] args) {
//		long start = System.currentTimeMillis();
//		
//		ConcurrentFixSizeArrayList3<String> stuList = new ConcurrentFixSizeArrayList3<String>(50);
////		CopyOnWriteArrayList<String> stuList = new CopyOnWriteArrayList<String>();
////		ConcurrentLinkedQueue<String> stuList = new ConcurrentLinkedQueue<String>();
//		
//		WriteReport writeReport = new WriteReport(stuList, 0, 1000000, "a");
//		WriteReport writeReport2 = new WriteReport(stuList, 1000000, 2000000, "b");
////		WriteReport writeReport3 = new WriteReport(stuList, 20000, 20500, "c");
////		ReadReport readReport = new ReadReport(stuList);
//		
////		ReportCallable writeReport = new ReportCallable(stuList, 0, 500, "a");
////		ReportCallable writeReport2 = new ReportCallable(stuList, 10000, 10500, "b");
//		
//		ExecutorService executor = Executors.newFixedThreadPool(3);
//		try {
////			executor.invokeAll(Lists.newArrayList(writeReport, writeReport2));
//			executor.submit(writeReport);
//			executor.submit(writeReport2);
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		executor.shutdown();
//		System.out.println("================");
//		while (true) {
//			if (executor.isTerminated()) {
//				System.out.println(stuList);
//				System.out.println(System.currentTimeMillis()-start);
//				break;
//			}
//		}
////		
//	}
	
	
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		
		ConcurrentFixSizeArrayList3<String> stuList = new ConcurrentFixSizeArrayList3<String>(50);
//		CopyOnWriteArrayList<String> stuList = new CopyOnWriteArrayList<String>();
//		ConcurrentLinkedQueue<String> stuList = new ConcurrentLinkedQueue<String>();
		
		WriteReport writeReport = new WriteReport(stuList, 0, 1000000, "a");
		WriteReport writeReport2 = new WriteReport(stuList, 1000000, 2000000, "b");
//		WriteReport writeReport3 = new WriteReport(stuList, 20000, 20500, "c");
//		ReadReport readReport = new ReadReport(stuList);
		
//		ReportCallable writeReport = new ReportCallable(stuList, 0, 500, "a");
//		ReportCallable writeReport2 = new ReportCallable(stuList, 10000, 10500, "b");
		
		ExecutorService executor = Executors.newFixedThreadPool(3);
		try {
//			executor.invokeAll(Lists.newArrayList(writeReport, writeReport2));
			executor.submit(writeReport);
			executor.submit(writeReport2);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		executor.shutdown();
		System.out.println("================");
		while (true) {
			if (executor.isTerminated()) {
				System.out.println(stuList);
				System.out.println(System.currentTimeMillis()-start);
				break;
			}
		}
//		
	}

}
