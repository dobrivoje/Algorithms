package Java67.primer1mojeizmene;

import lombok.Getter;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Getter
public class Worker extends Thread {

	private final int            delay;
	private final CountDownLatch latch;

	public Worker(int delay, CountDownLatch countDownLatch, String threadName) {
		super( threadName );
		this.delay = delay;
		this.latch = countDownLatch;
	}

	@Override
	public void run() {
		try {
			TimeUnit.MILLISECONDS.sleep( delay );
			latch.countDown();
			System.out.println( Thread.currentThread().getName() + " has finished. Delay = " + delay );
		} catch (InterruptedException e) {
			System.err.println( "Greška : " + e.getMessage() );
		}
	}
}
