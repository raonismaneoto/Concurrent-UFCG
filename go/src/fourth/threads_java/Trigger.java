package threads_java;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Trigger {
	private static long getMemory() {
		return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
	}
	
	public static void main(String[] args) throws InterruptedException {
		long initialMemory = getMemory();
		
		int numberOfRuns = 100;
		for (int numberOfThreads = 1; numberOfThreads <= 131072; numberOfThreads*=3) {
		
			Channel<Integer> channel = new ChannemImpl(numberOfThreads);
			ExecutorService threadPool = Executors.newFixedThreadPool(2 * numberOfRuns);
			
			for (int i = 0; i < 2 * numberOfThreads; ++i) {
				if (i % 2 == 0) {
					threadPool.execute(new Producer(channel, numberOfRuns));
				} else {
					threadPool.execute(new Consumer(channel, numberOfRuns));
				}
			}
			
			threadPool.shutdown();
			threadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
			
			long finalMemory = getMemory();
			
			System.out.printf("%dKb of memory used for %d threads\n", (finalMemory - initialMemory) / 1024, numberOfThreads);
			
		}
	}
}