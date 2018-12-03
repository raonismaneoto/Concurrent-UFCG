package fourth;

import second.ReliableRequest;

public class RequestExecuter implements Runnable{
	
	public void run() {
		ReliableRequest r = new ReliableRequest();
		while(true) {
			try {
				System.out.println(r.reliableRequest());
				Thread.sleep(500);
			} catch (InterruptedException e) {
				return;
			}
		}
	}
}
