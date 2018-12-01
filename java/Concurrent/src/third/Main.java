package third;

public class Main {
	
	private String data;
	
	public String reliableRequestCallerInterface() throws InterruptedException {
		ReliableRequestCaller r = new ReliableRequestCaller(this);
		Thread t1 = new Thread(r);
		t1.start();
		
		Thread.sleep(2000);
		if(this.data == null) {
			System.out.println("Time is over!!");
		} else {
			System.out.println(this.data);
		}
		return "";
	}
	
	public void setData(String data) {
		this.data = data;
	}
	
	public static void main(String args[]) {
		Main m = new Main();
		try {
			m.reliableRequestCallerInterface();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
