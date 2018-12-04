package fifth;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CopyOnWriteArrayListVsSynchronizedList {
	public static int nThreads;
	public static boolean flag;
	public static GenerateCsv generateCsv;
	
	public static List<Integer> copyOnWriteArrayList;
	public static List<Integer> synchronizedList;
	
	public static void main(String[] args) throws InterruptedException, IOException {
		generateCsv = new GenerateCsv("copy_x_sync_List.csv");
		
		flag = false;
		
		copyOnWriteArrayList = new CopyOnWriteArrayList<Integer>();
		performanceAnalysis(copyOnWriteArrayList, flag);
		
		synchronizedList = Collections.synchronizedList(new ArrayList<Integer>());
		performanceAnalysis(synchronizedList, flag);
		
		for (int i = 0; i < 20005; i++) {
			copyOnWriteArrayList.add(1);
			synchronizedList.add(1);
		}
		
		flag = true;
		
		performanceAnalysis(copyOnWriteArrayList, flag);
		
		performanceAnalysis(synchronizedList, flag);
		
		generateCsv.closeCsv();
	}
	
	public static void performanceAnalysis(List<Integer> l, boolean flag) throws InterruptedException, IOException {
		System.out.println("Performance analysis for: " + l.getClass());
		for (nThreads = 1; nThreads <= 1024; nThreads*=2) {
			System.out.println("  Number of Threads: " + nThreads);
			long sumTime = 0;
			
			for (int i = 0; i < 50; i++) {
				long startTime = System.currentTimeMillis();
				
				ExecutorService executorService = Executors.newFixedThreadPool(nThreads);
				for (int j = 0; j < nThreads; j++) {
					executorService.execute(new Runnable() {
												
						@Override
						public void run() {
							for (int k = 0; k < 20000/nThreads; k++) {
								if (!flag) {
									Integer randomNumber = (int) Math.ceil(Math.random() * 507217);
									l.add(randomNumber);
								} else {
									Integer number = (int) Math.ceil(Math.random() * (l.size()-1));
									@SuppressWarnings("unused")
									Integer value = l.get(number);
								}
							}
						}						
					});
				}
				
				executorService.shutdown();
				executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
				
				long endTime = System.currentTimeMillis();
				long totalTime = (endTime - startTime);
				sumTime += totalTime;
				if (!flag) {
					System.out.println("    20k of adds in " + totalTime + " ms");
					generateCsv.addLine("0", l.getClass().toString(), "" + nThreads, "" + totalTime);
					l.clear();
				} else {
					System.out.println("    20K of gets in " + totalTime + " ms");
					generateCsv.addLine("1", l.getClass().toString(), "" + nThreads, "" + totalTime);
				}
			}
			long averageTime = sumTime/15;
			System.out.println("  The average time is " + averageTime + " ms");
			System.out.println("  The sum time is " + sumTime + " ms");
		}
		System.out.println();
	}
	
}
