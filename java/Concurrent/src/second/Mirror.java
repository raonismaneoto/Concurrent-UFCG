package second;

import java.sql.Time;

public class Mirror<T> implements Runnable {
	
	private ReliableRequest client;
	private String serverName;
	
	public Mirror(ReliableRequest client, String serverName) {
		this.client = client;
		this.serverName = serverName;
	}
	
	@SuppressWarnings("finally")
	public void run() {
		while(true) {
			if(this.client.getLock().tryLock()) {
				try {
					if(!this.client.hasWritten()) {
						client.write(this.serverName);
					}
				} finally {
					this.client.getLock().unlock();
					return;
				}
			} else {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {}
			}
		}
		
	}
}
