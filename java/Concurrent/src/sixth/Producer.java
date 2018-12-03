package sixth;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import first.Channel;

public class Producer implements Runnable{

	private Channel<Integer> chan;
	
	public Producer(Channel<Integer> chan) {
		this.chan = chan;
	}
	
	@Override
	public void run() {
		while (true) {
			Integer produced = new Random().nextInt(11);
			System.out.println("produced " + produced);
			chan.putMessage(produced);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
