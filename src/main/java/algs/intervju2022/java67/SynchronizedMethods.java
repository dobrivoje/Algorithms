package algs.intervju2022.java67;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <a href="https://www.baeldung.com/java-synchronized">...</a>
 */

public class SynchronizedMethods {

	private       int    counter = 0;

	private final Lock   lock    = new ReentrantLock();
	private final Object lock1   = new Object();
	private final Object lock2   = new Object();

	public synchronized int increment() {
		System.err.println("? inc - try, wait some time...");
		try {
			TimeUnit.SECONDS.sleep( 4 );
		} catch (InterruptedException e) {
		}

		System.err.println("# inc. done!");
		return ++counter;

		/*synchronized (lock) {
			return ++counter;
		}*/

		/*lock.lock();
		try {
			return ++counter;
		} finally {
			lock.unlock();
		}*/
	}

	public synchronized int getCounter() {
		/*synchronized (lock) {
			return counter;
		}*/

		/*lock.lock();
		try {
			return counter;
		} finally {
			lock.unlock();
		}*/

		System.err.println("? get - ack");
		return counter;
	}
}
