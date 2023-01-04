package digital_ocean.nevezano_vezbe.TIJ4;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class EvenGenerator extends IntGenerator {

	private int currEvenVal = 0;

	private final Lock lock = new ReentrantLock();

	@Override
	public int next() {
		lock.lock();

		try {
			currEvenVal++;
			currEvenVal++;

			return currEvenVal;
		} finally {
			lock.unlock();
		}
	}

	public static void main(String[] args) {
		EvenChecker.test( new EvenGenerator(), 1 );
	}
}
