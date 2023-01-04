package Java67.primer1;

import java.util.concurrent.CountDownLatch;

/**
 * @author д06ри, dobri7@gmail.com
 */
class Worker extends Thread {

	private final int            delay;
	private final CountDownLatch latch;

	public Worker(int delay, CountDownLatch latch, String name) {
		super( name );
		this.delay = delay;
		this.latch = latch;
	}

	@Override
	public void run() {
		try {
			Thread.sleep( delay );

			/*if (1 == 1) {
				throw new RuntimeException( "666" );
			}*/

			System.out.println( Thread.currentThread().getName()
										+ " has finished" );
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			latch.countDown();
		}
	}
}
