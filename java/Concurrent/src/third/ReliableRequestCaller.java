package third;

import second.ReliableRequest;

public class ReliableRequestCaller implements Runnable{
	
	private Main client;
	
	public ReliableRequestCaller(Main client) {
		this.client = client;
	}
	
	public void run() {
		ReliableRequest r = new ReliableRequest();
		try {
			client.setData(r.reliableRequest());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
