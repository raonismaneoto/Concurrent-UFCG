package second;

import java.sql.Time;

public class Mirror<T> implements Runnable {
	
	private Main client;
	private String serverName;
	
	public Mirror(Main client, String serverName) {
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
						System.out.println(this.serverName + " I've made it");
					}
					else {
						System.out.println(this.serverName + " couldn't write");
					}
				} finally {
					this.client.getLock().unlock();
					return;
				}
			} else {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
}
