package threads_java;

public class Consumer extends Thread {
	private int nRuns;
	private Channel<Integer> chan;

	public Consumer(Channel<Integer> chan, int nRuns) {
		this.nRuns = nRuns;
		this.chan = chan;
	}

	@Override
	public void run() {
		for (int i = 0; i < this.nRuns; ++i) {
			Integer n = chan.get();
			if (n != 0) {
				n = 0;
			} else {
				n = 1;
			}
		}
	}
}