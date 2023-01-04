package Java67.primer1_nijedobro;

import Java67.primer2_dobar.RunnableWithLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

class Worker extends Thread implements RunnableWithLatch {

	private final int            delay;
	private       CountDownLatch latch;

	public Worker(int delay, String name) {
		super( name );
		this.delay = delay;
	}

	public synchronized Worker withLatch(CountDownLatch latch) {
		setLatch( latch );
		return this;
	}

	public synchronized Worker andStart() {
		this.start();
		return this;
	}

	@Override
	public void run() {
		try {
			TimeUnit.MILLISECONDS.sleep( delay );

			/*if (1 == 1) {
				throw new RuntimeException( "666" );
			}*/

			System.out.println( Thread.currentThread().getName() + " has finished" );
		} catch (InterruptedException e) {
			throw new RuntimeException( e );
		} finally {
			latch.countDown();
		}
	}

	@Override
	public void setLatch(CountDownLatch latch) {
		this.latch = latch;
	}
}
