package algs.intervju2022.tredovi.BoundedBuffer;

import java.util.logging.Level;
import java.util.logging.Logger;

public class BoundedBuffer<T> {

	private static final Logger logger = Logger.getLogger( BoundedBuffer.class.getName() );

	private final T[] buffer;
	private       int putPos, takePos, count;

	public BoundedBuffer(int limit) {
		this.buffer = (T[]) new Object[limit];
		putPos = takePos = count = 0;
	}

	public synchronized void put(T object) {
		try {
			while (isFull()) {
				wait();
			}
		} catch (Exception e) {
			logger.log( Level.ALL, e.getMessage() );
		}

		doPutObj( object );
		notifyAll();
	}

	protected void doPutObj(T object) {
		buffer[putPos] = object;
		if (putPos++ == buffer.length) {
			putPos = 0;
		}

		count++;
	}

	protected synchronized T doTake() {
		T t = buffer[takePos];
		if (++takePos == buffer.length) {
			takePos = 0;
		}
		count--;

		return t;
	}

	public synchronized T take() {
		while (isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
				logger.log( Level.ALL, e.getMessage() );
			}
		}

		T t = doTake();
		notifyAll();

		return t;
	}

	public synchronized boolean isEmpty() {
		return buffer.length == 0;
	}

	public synchronized boolean isFull() {
		return buffer.length <= count;
	}
}
