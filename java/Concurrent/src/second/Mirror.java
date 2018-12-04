package second;

import java.sql.Time;
import java.util.Random;

import first.Channel;

public class Mirror<T> implements Runnable {
	
	private String serverName;
	private Channel<String> chan;
	
	public Mirror(String serverName, Channel<String> chan) {
		this.serverName = serverName;
		this.chan = chan;
	}
	
	public void run() {
		while(true) {
			try {
				Thread.sleep(new Random().nextInt(11)*5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			chan.putMessage("Answered by " + this.serverName);
		}
		
	}
}
