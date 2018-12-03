package fourth;

import java.util.Random;

public class Main {
	
	public static class AbortThread implements Runnable {
		
		private Thread threadToAbort;
		
		public AbortThread(Thread threadToAbort) {
			this.threadToAbort = threadToAbort; 
		}
		
		public void run() {
			try {
				Thread.sleep((new Random().nextInt(11))*1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			this.threadToAbort.interrupt();
		}
	}
	
	public static void main(String args[]) {
		RequestExecuter r = new RequestExecuter();
		Thread t = new Thread(r);
		
		AbortThread a = new AbortThread(t);
		Thread t2 = new Thread(a);
		
		t.start();
		t2.start();
		
		try {
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
