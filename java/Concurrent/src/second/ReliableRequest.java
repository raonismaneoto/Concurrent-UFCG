package second;

import first.Channel;
import first.ChannelImpl;

public class ReliableRequest {
	private Channel<String> chan;
	
	public ReliableRequest() {
		chan = new ChannelImpl<String>(1);
	}
	
	public String reliableRequest() {
		Thread mirror1 = new Thread(new Mirror("mirror1.com", this.chan));
		Thread mirror2 = new Thread(new Mirror("mirror2.br", this.chan));
		Thread mirror3 = new Thread(new Mirror("mirror3.edu", this.chan));
		
		mirror1.setDaemon(true);
		mirror2.setDaemon(true);
		mirror3.setDaemon(true);
		
		mirror1.start();
		mirror2.start();
		mirror3.start();
		
		return this.chan.takeMessage();
	}
	
	public String request() {
		return reliableRequest();
	}
	
	public static void main(String[] args) {
		ReliableRequest m = new ReliableRequest();
		String result = m.request();
		System.out.println(result);
	}
}
