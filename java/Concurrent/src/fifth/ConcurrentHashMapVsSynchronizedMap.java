package fifth;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ConcurrentHashMapVsSynchronizedMap {
	
	public static int nThreads;
	public static boolean flag;
	public static GenerateCsv generateCsv;
	
	public static Map<Integer, Integer> concurrentHashMap;
	public static Map<Integer, Integer> synchronizedMap;
	
	public static void main(String[] args) throws InterruptedException, IOException {
		generateCsv = new GenerateCsv("conc_x_sync_Map.csv");
		
		flag = false;
		
		concurrentHashMap = new ConcurrentHashMap<Integer, Integer>();
		performanceAnalysis(concurrentHashMap, flag);
		
		synchronizedMap = Collections.synchronizedMap(new HashMap<Integer, Integer>());
		performanceAnalysis(synchronizedMap, flag);
		
		flag = true;
		
		concurrentHashMap = new ConcurrentHashMap<Integer, Integer>();
		performanceAnalysis(concurrentHashMap, flag);
		
		synchronizedMap = Collections.synchronizedMap(new HashMap<Integer, Integer>());
		performanceAnalysis(synchronizedMap, flag);
		
		generateCsv.closeCsv();
	}

	public static void performanceAnalysis(Map<Integer, Integer> m, boolean flag) throws InterruptedException, IOException {
		System.out.println("Performance analysis for: " + m.getClass());
		for (nThreads = 1; nThreads <= 1024; nThreads*=2) {
			System.out.println("  Number of Threads: " + nThreads);
			long sumTime = 0;
			long totalTime = 0;
			for (int i = 0; i < 50; i++) {
				totalTime = 0;
				long startTime = System.currentTimeMillis();
				
				ExecutorService executorService = Executors.newFixedThreadPool(nThreads);
				for (int j = 0; j < nThreads; j++) {
					executorService.execute(new Runnable() {
												
						@Override
						public void run() {
							for (int k = 0; k < 1000000/nThreads; k++) {
								Integer randomNumber = (int) Math.ceil(Math.random() * 507217);
								
								if (flag) {
									@SuppressWarnings("unused")
									Integer value = m.get(randomNumber);
								} else {
									m.put(randomNumber, randomNumber);
								}
							}
						}				
					});
				}
				
				executorService.shutdown();
				executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
				
				long endTime = System.currentTimeMillis();
				totalTime = (endTime - startTime);
				sumTime += totalTime;
				if (flag) {
					System.out.println("    1KK of gets in " + totalTime + " ms");
					generateCsv.addLine("1", m.getClass().toString(), "" + nThreads, "" + totalTime);
				} else {
					System.out.println("    1KK of puts in " + totalTime + " ms");
					generateCsv.addLine("0", m.getClass().toString(), "" + nThreads, "" + totalTime);
				}
			}
			long averageTime = sumTime/15;
			System.out.println("  The average time is " + averageTime + " ms");
			System.out.println("  The sum time is " + sumTime + " ms");
		}
		System.out.println();
	}
	
}
