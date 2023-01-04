package Java67.primer2_dobar;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CallableWorker implements CallableWithLatch<Integer> {

	CountDownLatch countDownLatch;
	final String name;
	final int    delay;

	public CallableWorker(String name, int delay) {
		this.name = name;
		this.delay = delay;
	}

	@Override
	public void setLatch(CountDownLatch countDownLatch) {
		this.countDownLatch = countDownLatch;
	}

	@Override
	public Integer call() throws Exception {
		TimeUnit.MILLISECONDS.sleep( delay );
		countDownLatch.countDown();
		System.out.println( "Task computed and finished by :" + name );

		return new Random().nextInt( 10 );
	}
}
