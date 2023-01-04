package digital_ocean.nevezano_vezbe.Filizofi;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Counter {

	private       long counter;
	private final Lock lock;

	public Counter() {
		this( 0 );
	}

	public Counter(long counter) {
		this.counter = counter;
		this.lock = new ReentrantLock();
	}

	public synchronized long getCounter() {
		return counter;
	}

	@Override
	public String toString() {
		return String.valueOf( getCounter() );
	}

	public synchronized void increment() {
//		counter++;

		try {
			lock.lock();
			counter++;
		} finally {
			lock.unlock();
		}
	}
}
