package first;

import java.util.Random;

public class Main {
	
	public static class Producer implements Runnable {
		private Channel chan;
		
		public Producer(Channel chan) {
			this.chan = chan;
		}

		@Override
		public void run() {
			while (true) {
				synchronized(this.chan) {
					while(this.chan.isFull()) {
						try {
							this.chan.wait();
						} catch (Exception e) {
						}
					}
					String produced = "" + new Random().nextInt(11);
					System.out.println("produced " + produced);
					try {
						this.chan.putMessage(produced);
						this.chan.notifyAll();
						Thread.sleep(100);
					} catch (Exception e) {
					}
				}
			}
		}
		
	}
	
	public static class Consumer implements Runnable {
		private Channel chan;
		
		public Consumer(Channel chan) {
			this.chan = chan;
		}

		@Override
		public void run() {
			while (true) {
				synchronized(this.chan) {
					while(this.chan.isEmpty()) {
						try {
							this.chan.wait();
						} catch (Exception e) {
						}
					}
					try {
						String message = this.chan.takeMessage();
						System.out.println("taken " + message);
					} catch (Exception e) {
					}
					this.chan.notifyAll();
				}
			}
		}
		
	}
	
	public static void main(String args[]) throws InterruptedException {
		Channel ch = new ChannelImpl(10);

		Producer p1 = new Producer(ch);
		Producer p2 = new Producer(ch);
		Producer p3 = new Producer(ch);
		
		Consumer c1 = new Consumer(ch);
		Consumer c2 = new Consumer(ch);
		
		Thread t1 = new Thread(p1);
		Thread t2 = new Thread(c1);
		Thread t3 = new Thread(p2);
		Thread t4 = new Thread(p3);
		Thread t5 = new Thread(c2);
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		
		t1.join();
		t2.join();
		t3.join();
		t4.join();
		t5.join();
	}
}
