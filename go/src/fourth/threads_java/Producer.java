package threads_java;

import java.util.Random;

public class Producer extends Thread {
	private Random rand;
	private int nRuns;
	private Channel<Integer> chan;

	public Producer(Channel<Integer> channel, int numberOfRuns) {
		this.rand = new Random(); 
		this.nRuns = numberOfRuns;
		this.chan = channel;
	}

	@Override
	public void run() {
		for (int i = 0; i < this.nRuns; ++i) {
			Integer n = (Math.abs(rand.nextInt()) % 100) + 1;
			chan.put(n);
		}
	}
}