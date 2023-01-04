package Java67.primer1;

import algs.Util.Utils;

import java.util.concurrent.CountDownLatch;

/**
 * @author д06ри, dobri7@gmail.com
 */
public class CountDownLatchDemo {

	public static void main(String args[]) throws InterruptedException {
		Utils.executionTime( () -> {
			CountDownLatch latch = new CountDownLatch( 4 );
			Worker first = new Worker( 3000, latch, "WORKER-1" );
			Worker second = new Worker( 1500, latch, "WORKER-2" );
			Worker third = new Worker( 2000, latch, "WORKER-3" );
			Worker fourth = new Worker( 2000, latch, "WORKER-4" );

			first.start();
			second.start();
			third.start();
			fourth.start();

			// Main thread will wait until all thread finished
			try {
				latch.await();
			} catch (InterruptedException e) {
				throw new RuntimeException( e );
			}

			System.out.println( Thread.currentThread().getName() + " has finished" );
		}, "test 1" );
	}
}
