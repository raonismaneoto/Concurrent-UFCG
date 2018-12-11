package threads_java;

import java.util.LinkedList;
import java.util.Queue;

public class ChannemImpl implements Channel<Integer> {
	private int cap;
	private Queue<Integer> integers;

	public ChannemImpl(int capacity) {
		this.cap = capacity;
		this.integers = new LinkedList<Integer>();
	}

	private boolean isEmpty() {
		return (this.integers.isEmpty());
	}

	private boolean isFull() {
		return (this.integers.size() == this.cap);
	}

	@Override
	public void put(Integer v) {
		synchronized (this) {
			while (isFull()) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			integers.add(v);
			notifyAll();
		}
	}

	@Override
	public Integer get() {
		synchronized (this) {
			while (isEmpty()) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			notifyAll();
			return integers.poll();
		}
	}

}